package com.felipesantos.ecommerce.order;

import com.felipesantos.ecommerce.customer.CustomerService;
import com.felipesantos.ecommerce.kafka.OrderConfirmationRequest;
import com.felipesantos.ecommerce.kafka.KafkaOrderProducer;
import com.felipesantos.ecommerce.orderline.OrderLineRequest;
import com.felipesantos.ecommerce.orderline.OrderLineService;
import com.felipesantos.ecommerce.payment.PaymentRequest;
import com.felipesantos.ecommerce.payment.PaymentService;
import com.felipesantos.ecommerce.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderLineService orderLineService;
    private final PaymentService paymentService;
    private final KafkaOrderProducer kafkaOrderProducer;
    private final OrderMapper orderMapper;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No order found with the provided ID :: %d", orderId)
                ));
    }

    @Transactional
    public Integer createOrder(OrderRequest orderRequest) {
        // using open feign
        var customer = customerService.findCustomerById(orderRequest.customerId());

        // using rest template
        var purchasedProducts = productService.purchaseProducts(orderRequest.products());
        var order = orderRepository.save(orderMapper.toOrder(orderRequest));
        saveOrderLines(orderRequest, order);

        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        // using open feign
        paymentService.requestOrderPayment(paymentRequest);

        // send order confirmation --> ms-notification (kafka)
        kafkaOrderProducer.sendOrderConfirmation(
                new OrderConfirmationRequest(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts)
        );

        return order.getId();
    }

    private void saveOrderLines(OrderRequest orderRequest, Order order) {
        for (var purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order,
                            purchaseRequest.productId(),
                            purchaseRequest.quantity())
            );
        }
    }
}
