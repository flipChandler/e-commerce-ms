package com.felipesantos.ecommerce.kafka;

import com.felipesantos.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotification(String orderReference,
                                  BigDecimal amount,
                                  PaymentMethod paymentMethod,
                                  String customerFirstName,
                                  String customerLastName,
                                  String customerEmail) {
}
