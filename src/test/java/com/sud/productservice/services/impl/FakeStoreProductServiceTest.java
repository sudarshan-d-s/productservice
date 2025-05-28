package com.sud.productservice.services.impl;

import com.sud.productservice.dtos.FakeStoreProductResponseDto;
import com.sud.productservice.exceptions.ProductNotFoundException;
import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class FakeStoreProductServiceTest {

    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    private final FakeStoreProductService fakeStoreProductService = new FakeStoreProductService(restTemplate);
    private final String FAKESTORE_API_BASE_URL = "https://fakestoreapi.com/products/";

    @Test
    void testWhenGetProductByIdThenReturnProduct() throws ProductNotFoundException {

        Product dummyProduct = getDummyProduct();
        Long dummyProductId = dummyProduct.getId();

        ResponseEntity<FakeStoreProductResponseDto> dtoResponseEntity =
                ResponseEntity.ofNullable(FakeStoreProductResponseDto.fromProduct(dummyProduct));

        Mockito.when(restTemplate.exchange(FAKESTORE_API_BASE_URL + dummyProductId,
                HttpMethod.GET, null,
                FakeStoreProductResponseDto.class)).thenReturn(dtoResponseEntity);

        Product product = fakeStoreProductService.getProductById(dummyProductId);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(1L, product.getId());
        Assertions.assertEquals("title", product.getTitle());
        Assertions.assertEquals("http://imageurl/img.jpg", product.getImageUrl());
    }

    @Test
    void testWhenGetProductByIdReturnsNullThenThrowProductNotFoundException() throws ProductNotFoundException {

        Long dummyProductId = 1L;

        ResponseEntity<FakeStoreProductResponseDto> dtoResponseEntity =
                ResponseEntity.ok().build();

        Mockito.when(restTemplate.exchange(FAKESTORE_API_BASE_URL + dummyProductId,
                HttpMethod.GET, null,
                FakeStoreProductResponseDto.class)).thenReturn(dtoResponseEntity);

        Assertions.assertThrows(ProductNotFoundException.class,
                () -> fakeStoreProductService.getProductById(dummyProductId));
    }

    @Test
    void getAllProducts() {

    }

    @Test
    void whenCreateProductThenReturnProduct() {
        Product dummyProduct = getDummyProduct();

        ResponseEntity<FakeStoreProductResponseDto> responseEntity = ResponseEntity.ok(
                FakeStoreProductResponseDto.fromProduct(dummyProduct));

        Mockito.when(restTemplate.exchange(Mockito.eq(FAKESTORE_API_BASE_URL),
                Mockito.eq(HttpMethod.POST), Mockito.any(),
                Mockito.eq(FakeStoreProductResponseDto.class))).thenReturn(responseEntity);

        Product product = fakeStoreProductService.createProduct(dummyProduct.getTitle(),
                dummyProduct.getDescription(), dummyProduct.getPrice(),
                dummyProduct.getImageUrl(), dummyProduct.getCategory().getName());

        Assertions.assertEquals(dummyProduct.getId(), product.getId());
        Assertions.assertEquals(dummyProduct.getTitle(), product.getTitle());
        Assertions.assertEquals(dummyProduct.getCategory().getName(), product.getCategory().getName());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
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