package com.felipesantos.ecommerce.orderline;

import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .order(orderLineRequest.order())
                .quantity(orderLineRequest.quantity())
                .productId(orderLineRequest.productId())
                .build();
    }
}
