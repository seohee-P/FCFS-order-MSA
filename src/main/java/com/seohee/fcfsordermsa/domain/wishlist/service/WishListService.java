package com.seohee.fcfsordermsa.domain.wishlist.service;

import com.seohee.fcfsordermsa.domain.product.entity.Product;
import com.seohee.fcfsordermsa.domain.product.repository.ProductRepository;
import com.seohee.fcfsordermsa.domain.user.entity.User;
import com.seohee.fcfsordermsa.domain.wishlist.dto.AddWishListItemRequestDto;
import com.seohee.fcfsordermsa.domain.wishlist.dto.UpdateWishListItemRequestDto;
import com.seohee.fcfsordermsa.domain.wishlist.dto.WishListRequestDto;
import com.seohee.fcfsordermsa.domain.wishlist.dto.WishListResponseDto;
import com.seohee.fcfsordermsa.domain.wishlist.entity.WishList;
import com.seohee.fcfsordermsa.domain.wishlist.entity.WishListItem;
import com.seohee.fcfsordermsa.domain.wishlist.repository.WishListItemRepository;
import com.seohee.fcfsordermsa.domain.wishlist.repository.WishListRepository;
import com.seohee.fcfsordermsa.global.exception.IllegalArgumentException;
import com.seohee.fcfsordermsa.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.seohee.fcfsordermsa.global.exception.ExceptionCode.*;

@RequiredArgsConstructor
@Service
public class WishListService {

    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;
    private final WishListItemRepository wishListItemRepository;


    public WishListResponseDto createWishList(UserDetailsImpl userDetails, WishListRequestDto requestDto) {

        User user = userDetails.getUser();

        WishList wishList = WishList.builder()
                .name(requestDto.getName())
                .user(user)
                .build();

        wishListRepository.save(wishList);

        return new WishListResponseDto(wishList);
    }

    public List<WishListResponseDto> getAllWishList(UserDetailsImpl userDetailsImpl) {

        User user = userDetailsImpl.getUser();

        List<WishListResponseDto> wishListResponseDtos = wishListRepository.findByUser(user).stream().map(WishListResponseDto::new).toList();

        return wishListResponseDtos;
    }

    public WishListResponseDto getWishList(Long wishListId) {

        WishList wishList = findByWishListId(wishListId);

        return new WishListResponseDto(wishList);
    }

    @Transactional
    public WishListResponseDto updateWishList(Long wishListId, WishListRequestDto requestDto) {

        WishList wishList = findByWishListId(wishListId);

        wishList.updateName(requestDto);

        return new WishListResponseDto(wishList);
    }

    public void deleteWishList(Long wishListId) {

        WishList wishList = findByWishListId(wishListId);

        wishListRepository.delete(wishList);
    }

    public WishListResponseDto addWishListItem(Long wishListId, AddWishListItemRequestDto requestDto) {

        Product product = findByProductId(requestDto.getProductId());

        WishList wishList = findByWishListId(wishListId);

        WishListItem wishListItem = WishListItem.builder()
                .product(product)
                .quantity(requestDto.getQuantity())
                .wishList(wishList)
                .build();

        wishListItemRepository.save(wishListItem);

        return new WishListResponseDto(wishList);
    }

    @Transactional
    public WishListResponseDto updateWishListItem(Long wishListItemId, UpdateWishListItemRequestDto requestDto) {

        WishListItem wishListItem = findByWishListItemId(wishListItemId);

        wishListItem.updateQuantity(requestDto);

        return new WishListResponseDto(wishListItem.getWishList());
    }

    public void deleteWishListItem(Long wishListItemId) {

        WishListItem wishListItem = findByWishListItemId(wishListItemId);

        wishListItemRepository.delete(wishListItem);
    }

    public Product findByProductId(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_PRODUCT_ID)
        );

        return product;
    }

    public WishList findByWishListId(Long wishListId) {

        WishList wishList = wishListRepository.findById(wishListId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_WISHLIST_ID)
        );

        return wishList;
    }

    public WishListItem findByWishListItemId(Long wishListItemId) {

        WishListItem wishListItem = wishListItemRepository.findById(wishListItemId).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_WISHLIST_ITEM_ID)
        );

        return wishListItem;
    }
}
