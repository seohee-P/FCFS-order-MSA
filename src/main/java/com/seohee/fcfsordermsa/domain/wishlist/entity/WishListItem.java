package com.seohee.fcfsordermsa.domain.wishlist.entity;

import com.seohee.fcfsordermsa.domain.product.entity.Product;
import com.seohee.fcfsordermsa.domain.wishlist.dto.UpdateWishListItemRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="wishlist_items")
@Entity
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlistitem_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private WishList wishList;


    @Builder
    public WishListItem(Long id, Product product, Integer quantity, WishList wishList) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.wishList = wishList;
    }

    public void updateQuantity(UpdateWishListItemRequestDto requestDto) {
        this.quantity = requestDto.getQuantity();
    }

}
