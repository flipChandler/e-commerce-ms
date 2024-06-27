package com.felipesantos.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(String id,
                              @NotBlank(message = "Customer firstName is required")
                              String firstName,
                              @NotBlank(message = "Customer lastName is required")
                              String lastName,
                              @NotBlank(message = "Customer email is required")
                              @Email(message = "Customer email is not a valid")
                              String email,
                              Address address) {
}
