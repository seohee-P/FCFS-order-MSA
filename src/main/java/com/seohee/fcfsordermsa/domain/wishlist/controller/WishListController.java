package com.seohee.fcfsordermsa.domain.wishlist.controller;

import com.seohee.fcfsordermsa.domain.wishlist.dto.*;
import com.seohee.fcfsordermsa.domain.wishlist.service.WishListService;
import com.seohee.fcfsordermsa.global.response.ApiResponse;
import com.seohee.fcfsordermsa.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class WishListController {

    private final WishListService wishListService;

    @PostMapping("/wishLists")
    public ApiResponse<WishListResponseDto> createWishList(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                                           @RequestBody WishListRequestDto requestDto){

        WishListResponseDto responseDto = wishListService.createWishList(userDetailsImpl, requestDto);

        return ApiResponse.ok(responseDto);
    }

    @GetMapping("/wishLists")
    public ApiResponse<List<WishListResponseDto>> getAllWishLists(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){

        List<WishListResponseDto> responseDtos = wishListService.getAllWishList(userDetailsImpl);

        return ApiResponse.ok(responseDtos);
    }

    @GetMapping("/wishLists/{wishListId}")
    public ApiResponse<WishListResponseDto> getWishList(@PathVariable Long wishListId){

        WishListResponseDto responseDto = wishListService.getWishList(wishListId);

        return ApiResponse.ok(responseDto);
    }

    @PatchMapping("/wishLists/{wishListId}")
    public ApiResponse<WishListResponseDto> updateWishList(@PathVariable Long wishListId,
                                                           @RequestBody WishListRequestDto requestDto){

        WishListResponseDto responseDto = wishListService.updateWishList(wishListId, requestDto);

        return ApiResponse.ok(responseDto);
    }

    @DeleteMapping("/wishLists/{wishListId}")
    public ApiResponse<?> deleteWishList(@PathVariable Long wishListId){

        wishListService.deleteWishList(wishListId);

        return ApiResponse.ok();
    }

    @PostMapping("/wishListItems/{wishListId}")
    public ApiResponse<WishListResponseDto> addWishListItem(@PathVariable Long wishListId,
                                                            @RequestBody AddWishListItemRequestDto requestDto) {

        WishListResponseDto responseDto = wishListService.addWishListItem(wishListId, requestDto);

        return ApiResponse.ok(responseDto);
    }


    @PatchMapping("/wishListItems/{wishListItemId}")
    public ApiResponse<WishListResponseDto> updateWishListItem(@PathVariable Long wishListItemId,
                                                               @RequestBody UpdateWishListItemRequestDto requestDto) {

        WishListResponseDto responseDto = wishListService.updateWishListItem(wishListItemId, requestDto);

        return ApiResponse.ok(responseDto);
    }

    @DeleteMapping("/wishListItems/{wishListItemId}")
    public ApiResponse<?> deleteWishListItem(@PathVariable Long wishListItemId){

        wishListService.deleteWishListItem(wishListItemId);

        return ApiResponse.ok();
    }
}
