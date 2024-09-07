package com.seohee.fcfsordermsa.domain.wishlist.dto;

import lombok.Getter;

@Getter
public class AddWishListItemRequestDto {

    private Long productId;
    private Integer quantity;

}
