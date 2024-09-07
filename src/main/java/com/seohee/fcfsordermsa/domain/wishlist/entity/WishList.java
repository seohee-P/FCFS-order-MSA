package com.seohee.fcfsordermsa.domain.wishlist.entity;

import com.seohee.fcfsordermsa.domain.user.entity.User;
import com.seohee.fcfsordermsa.domain.wishlist.dto.WishListRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "wishlists")
@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "wishList")
    private List<WishListItem> wishListItems = new ArrayList<>();

    @Builder
    public WishList(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void updateName(WishListRequestDto requestDto) {
        this.name = requestDto.getName();
    }
}
