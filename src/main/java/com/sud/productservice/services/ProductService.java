package com.sud.productservice.services;

import com.sud.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long productId);

    List<Product> getAllProducts();
}
