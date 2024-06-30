package com.felipesantos.ecommerce.orderline;

import com.felipesantos.ecommerce.order.Order;

public record OrderLineRequest(Integer id,
                               Order order,
                               Integer productId,
                               double quantity) {
}
