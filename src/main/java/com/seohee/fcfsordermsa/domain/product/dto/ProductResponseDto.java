package com.seohee.fcfsordermsa.domain.product.dto;

import com.seohee.fcfsordermsa.domain.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private Long id;

    private String name;

    private String description;

    private String category;

    private Integer price;

    private Integer stock_quantity;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.stock_quantity = product.getStockQuantity();
    }
}
