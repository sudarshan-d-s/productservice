package com.sud.productservice.controllers;


import com.sud.productservice.dtos.ProductRequestDto;
import com.sud.productservice.dtos.ProductResponseDto;
import com.sud.productservice.exceptions.ProductNotFoundException;
import com.sud.productservice.models.Product;
import com.sud.productservice.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        Product prod = productService.createProduct(productRequestDto.toProduct());
        return ProductResponseDto.fromProduct(prod);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }

    @PutMapping()
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto dto){
        return ProductResponseDto.fromProduct(productService.updateProduct(dto.toProduct()));
    }

}
