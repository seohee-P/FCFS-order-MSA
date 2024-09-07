package com.seohee.fcfsordermsa.domain.product.dto;

import com.seohee.fcfsordermsa.domain.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    private Long id;

    private String name;

    private String description;

    private String category;

    private Integer price;

    private Integer stockQuantity;


    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .category(category)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

}
