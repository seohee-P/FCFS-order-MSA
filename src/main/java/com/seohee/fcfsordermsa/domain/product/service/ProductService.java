package com.seohee.fcfsordermsa.domain.product.service;

import com.seohee.fcfsordermsa.domain.product.dto.ProductRequestDto;
import com.seohee.fcfsordermsa.domain.product.dto.ProductResponseDto;
import com.seohee.fcfsordermsa.domain.product.entity.Product;
import com.seohee.fcfsordermsa.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto registerProduct(ProductRequestDto requestDto) {

        Product product = requestDto.toEntity();

        productRepository.save(product);

        return new ProductResponseDto(product);
    }

    public ProductResponseDto getProduct(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("Product not found"));

        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProduct() {

        List<ProductResponseDto> products = productRepository.findAll()
                .stream().map(ProductResponseDto::new).toList();

        return products;
    }
}