package com.seohee.fcfsordermsa.domain.product.controller;

import com.seohee.fcfsordermsa.domain.product.dto.ProductRequestDto;
import com.seohee.fcfsordermsa.domain.product.dto.ProductResponseDto;
import com.seohee.fcfsordermsa.domain.product.service.ProductService;
import com.seohee.fcfsordermsa.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ApiResponse<ProductResponseDto> registerProduct(@RequestBody ProductRequestDto requestDto) {

        ProductResponseDto responseDto = productService.registerProduct(requestDto);

        return ApiResponse.ok(responseDto);
    }

    @GetMapping("/products")
    public ApiResponse<List<ProductResponseDto>> getAllProduct() {

        List<ProductResponseDto> responseDtoList = productService.getAllProduct();

        return ApiResponse.ok(responseDtoList);
    }

    @GetMapping("/products/{productId}")
    public ApiResponse<ProductResponseDto> getProduct(@PathVariable Long productId) {

        ProductResponseDto responseDto = productService.getProduct(productId);

        return ApiResponse.ok(responseDto);
    }

}
