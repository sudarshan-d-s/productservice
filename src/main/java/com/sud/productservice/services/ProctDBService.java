package com.sud.productservice.services;

import com.sud.productservice.exceptions.ProductNotFoundException;
import com.sud.productservice.models.Product;
import com.sud.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productdbservice")
public class ProctDBService implements  ProductService{

    private ProductRepository productRepository;

    public ProctDBService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Product prod = productRepository.save(product);
        return prod;
    }

    @Override
    public Product updateProduct(Product product) {
        Product prod = productRepository.save(product);
        return prod;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
