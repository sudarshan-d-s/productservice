package com.sud.productservice.services;

import com.sud.productservice.models.Product;

public interface ProductService {

    Product getProductById(Long productId);
}
