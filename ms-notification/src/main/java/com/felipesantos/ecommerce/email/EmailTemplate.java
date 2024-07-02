package com.felipesantos.ecommerce.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {

    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation"),
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed");

    private final String template;
    private final String subject;
}
