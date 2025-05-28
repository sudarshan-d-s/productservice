package com.sud.productservice.controllers;


import com.sud.productservice.auth.AuthCommons;
import com.sud.productservice.dtos.ProductRequestDto;
import com.sud.productservice.dtos.ProductResponseDto;
import com.sud.productservice.dtos.UserDto;
import com.sud.productservice.exceptions.ProductNotFoundException;
import com.sud.productservice.models.Product;
import com.sud.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;
    private final AuthCommons authCommons;

    public ProductController(@Qualifier("productdbservice") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponseDto getProductById(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        Product product = productService.getProductById(productId);
        return ProductResponseDto.fromProduct(product);
    }

    @GetMapping()
    public List<ProductResponseDto> getAllProduct(){

        List<Product> products = productService.getAllProducts();

        return products.stream()
                .map(ProductResponseDto::fromProduct).toList();
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto,
                                            @RequestHeader("Authorization") String token){

        UserDto userDto = authCommons
                .validateToken(token);
        if(userDto == null) {
            throw new RuntimeException("Invalid Token");
        }

        Product product = productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategoryName()
        );
        return ProductResponseDto.fromProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId, @RequestHeader("Authorization") String token){

            UserDto userDto = authCommons
                    .validateToken(token);
            if(userDto == null) {
                throw new RuntimeException("Invalid Token");
            }

        productService.deleteProduct(productId);
    }

    @PutMapping()
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto dto, @RequestHeader("Authorization") String token){

        UserDto userDto = authCommons
                .validateToken(token);
        if(userDto == null) {
            throw new RuntimeException("Invalid Token");
        }
        return ProductResponseDto.fromProduct(productService.updateProduct(dto.toProduct()));
    }

}
