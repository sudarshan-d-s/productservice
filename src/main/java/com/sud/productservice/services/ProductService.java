package com.sud.productservice.services;

import com.sud.productservice.exceptions.ProductNotFoundException;
import com.sud.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long productId);

}
