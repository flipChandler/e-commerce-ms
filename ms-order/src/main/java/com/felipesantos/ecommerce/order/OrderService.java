package com.felipesantos.ecommerce.order;

import com.felipesantos.ecommerce.customer.CustomerService;
import com.felipesantos.ecommerce.orderline.OrderLineRequest;
import com.felipesantos.ecommerce.orderline.OrderLineService;
import com.felipesantos.ecommerce.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderLineService orderLineService;
    private final OrderMapper orderMapper;

    public Integer createOrder(OrderRequest orderRequest) {
        // using open feign
        var customer = customerService.findCustomerById(orderRequest.customerId());

        // using rest template
        productService.purchaseProducts(orderRequest.products());

        var order = orderRepository.save(orderMapper.toOrder(orderRequest));

        for (var purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order,
                            purchaseRequest.productId(),
                            purchaseRequest.quantity())
            );
        }

        // todo: start payment process
        // todo: send order confirmation --> ms-notification (kafka)
        return order.getId();
    }
}
