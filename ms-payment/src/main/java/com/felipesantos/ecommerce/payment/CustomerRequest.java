package com.felipesantos.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerRequest(String id,
                              @NotBlank(message = "First name is required")
                              String firstName,
                              @NotBlank(message = "Last name is required")
                              String lastName,
                              @Email(message = "The email customer is not correctly formatted")
                              @NotBlank(message = "Email is required")
                              String email) {
}
