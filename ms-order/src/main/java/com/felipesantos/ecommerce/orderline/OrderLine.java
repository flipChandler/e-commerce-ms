package com.felipesantos.ecommerce.orderline;

import com.felipesantos.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_LINE")
    @SequenceGenerator(name = "SEQ_ORDER_LINE", sequenceName = "SEQ_ORDER_LINE", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "order_fk"))
    private Order order;
    private Integer productId;
    private double quantity;
}
