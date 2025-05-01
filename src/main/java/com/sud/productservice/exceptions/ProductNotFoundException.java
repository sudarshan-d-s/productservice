package com.sud.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(Long productId) {
        super(String.format("Product with Product Id %s not found!", productId));
    }
}
