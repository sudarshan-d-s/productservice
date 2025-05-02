package com.sud.productservice.services.impl;

import com.sud.productservice.exceptions.ProductNotFoundException;
import com.sud.productservice.models.Product;
import com.sud.productservice.repositories.ProductRepository;
import com.sud.productservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productdbservice")
public class ProductDBService implements ProductService {

    private final ProductRepository productRepository;

    public ProductDBService(ProductRepository productRepository) {
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
    public Product createProduct(String title, String description, Double price,
                                 String imageUrl, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        return null;
    }

//    @Override
//    public Product createProduct {
//        Product prod = productRepository.save(product);
//        return prod;
//    }

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
