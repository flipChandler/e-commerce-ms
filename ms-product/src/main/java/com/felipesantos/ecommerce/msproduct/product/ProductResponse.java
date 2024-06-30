package com.felipesantos.ecommerce.msproduct.product;

import com.felipesantos.ecommerce.msproduct.category.CategoryResponse;

import java.math.BigDecimal;

public record ProductResponse(Integer id,
                              String name,
                              String description,
                              double availableQuantity,
                              BigDecimal price,
                              CategoryResponse category) {
}
