package com.seohee.fcfsordermsa.domain.wishlist.dto;

import com.seohee.fcfsordermsa.domain.wishlist.entity.WishListItem;
import lombok.Getter;

@Getter
public class WishListItemResponseDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Integer productStockQuantity;

    public WishListItemResponseDto(WishListItem wishListItem) {
        this.id = wishListItem.getId();
        this.productId = wishListItem.getProduct().getId();
        this.quantity = wishListItem.getQuantity();
        this.productName = wishListItem.getProduct().getName();
        this.productDescription = wishListItem.getProduct().getDescription();
        this.productPrice = wishListItem.getProduct().getPrice();
        this.productStockQuantity = wishListItem.getProduct().getStockQuantity();
    }

}
