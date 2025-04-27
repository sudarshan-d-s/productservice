package com.sud.productservice.services;

import com.sud.productservice.dtos.FakeStoreProductDto;
import com.sud.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    FakeStoreProductDto createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long productId);

}
