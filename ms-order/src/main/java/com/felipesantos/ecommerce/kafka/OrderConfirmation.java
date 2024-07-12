package com.felipesantos.ecommerce.kafka;

import com.felipesantos.ecommerce.customer.CustomerResponse;
import com.felipesantos.ecommerce.order.PaymentMethod;
import com.felipesantos.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(String orderReference,
                                BigDecimal totalAmount,
                                PaymentMethod paymentMethod,
                                CustomerResponse customer,
                                List<PurchaseResponse> products) {
}
