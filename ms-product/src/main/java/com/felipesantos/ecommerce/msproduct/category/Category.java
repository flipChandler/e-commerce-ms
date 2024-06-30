package com.felipesantos.ecommerce.msproduct.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipesantos.ecommerce.msproduct.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORY")
    @SequenceGenerator(name = "SEQ_CATEGORY", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
    private Integer id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public static Category of(CategoryResponse categoryResponse) {
        return Category.builder()
                .id(categoryResponse.id())
                .name(categoryResponse.name())
                .description(categoryResponse.description())
                .build();
    }
}
