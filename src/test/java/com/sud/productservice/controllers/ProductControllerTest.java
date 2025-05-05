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

        Product product = getDummyProduct();
        Long productId = product.getId();

        Mockito.when(productService.getProductById(productId)).thenReturn(product);

        ProductResponseDto productDto = productController.getProductById(productId);

        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(1L, product.getId());
        Assertions.assertEquals("title", product.getTitle());
        Assertions.assertEquals("http://imageurl/img.jpg", product.getImageUrl());

    }

    private Product getDummyProduct(){
        Long dummyProductId = 1L;

        Category dummyCategory = new Category();
        dummyCategory.setId(2L);
        dummyCategory.setName("somecat");
        dummyCategory.setDescription("some desc");

        Product dummyProduct = new Product();
        dummyProduct.setId(dummyProductId);
        dummyProduct.setCategory(dummyCategory);
        dummyProduct.setTitle("title");
        dummyProduct.setImageUrl("http://imageurl/img.jpg");
        dummyProduct.setPrice(30.00);

        return dummyProduct;
    }
}