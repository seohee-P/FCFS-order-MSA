package com.seohee.fcfsordermsa.domain.wishlist.dto;

import com.seohee.fcfsordermsa.domain.wishlist.entity.WishList;
import lombok.Getter;

import java.util.List;

@Getter
public class WishListResponseDto {

    private Long id;
    private String name;
    private List<WishListItemResponseDto> items;

    public WishListResponseDto(WishList wishList) {
        this.id = wishList.getId();
        this.name = wishList.getName();
        this.items = wishList.getWishListItems().stream().map(WishListItemResponseDto::new).toList();
    }
}
