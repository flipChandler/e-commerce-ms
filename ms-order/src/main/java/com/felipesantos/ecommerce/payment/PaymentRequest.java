package com.felipesantos.ecommerce.payment;

import com.felipesantos.ecommerce.customer.CustomerResponse;
import com.felipesantos.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(BigDecimal amount,
                             PaymentMethod paymentMethod,
                             Integer orderId,
                             String orderReference,
                             CustomerResponse customer) {
}
