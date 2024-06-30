package com.felipesantos.ecommerce.msproduct.handler;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
