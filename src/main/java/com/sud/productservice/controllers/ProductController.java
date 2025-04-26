package com.sud.productservice.controllers;


import com.sud.productservice.dtos.ProductResponseDto;
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
    public ProductResponseDto getProductById(@PathVariable("productId") Long productId){
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
    public Product createProduct(){
        return null;
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){

    }

    @PatchMapping("/{productId}")
    public void updateProduct(@PathVariable Long productId){

    }

}
