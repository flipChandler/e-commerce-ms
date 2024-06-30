package com.felipesantos.ecommerce.customer;

import com.felipesantos.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;

    public CustomerResponse findCustomerById(String customerId) {
        return customerClient.findCustomerById(customerId)
                .orElseThrow(() -> new BusinessException("Can not create order:: No customer exists with the provided ID :: " + customerId));
    }
}
