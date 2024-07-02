package com.felipesantos.ecommerce.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentClient paymentClient;

    public Integer requestOrderPayment(PaymentRequest paymentRequest) {
        return paymentClient.requestOrderPayment(paymentRequest);
    }
}
