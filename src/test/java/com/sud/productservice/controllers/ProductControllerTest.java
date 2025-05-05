package com.sud.productservice.controllers;

import com.sud.productservice.dtos.ProductResponseDto;
import com.sud.productservice.exceptions.ProductNotFoundException;
import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import com.sud.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ProductControllerTest{

    @MockitoBean
    @Qualifier("productfakestoreservice")
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    public  void testWhenGetByIdReturnResult() throws ProductNotFoundException {
        Long productId = 1L;

        Category category = new Category();
        category.setId(2L);
        category.setName("somecat");
        category.setDescription("some desc");

        Product product = new Product();
        product.setId(productId);
        product.setCategory(category);
        product.setTitle("title");
        product.setImageUrl("http://imageurl/img.jpg");
        product.setPrice(30.00);

        Mockito.when(productService.getProductById(productId)).thenReturn(product);

        ProductResponseDto productDto = productController.getProductById(productId);

        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(1L, product.getId());
        Assertions.assertEquals("title", product.getTitle());
        Assertions.assertEquals("http://imageurl/img.jpg", product.getImageUrl());

    }


}