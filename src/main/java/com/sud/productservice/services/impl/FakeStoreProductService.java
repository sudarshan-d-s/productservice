package com.sud.productservice.services.impl;

import com.sud.productservice.dtos.FakeStoreProductDto;
import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import com.sud.productservice.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    private final String FAKESTORE_API_BASE_URL = "https://fakestoreapi.com/products/";

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.
                getForObject(FAKESTORE_API_BASE_URL+productId, FakeStoreProductDto.class);
        if(null == fakeStoreProductDto){
            return null;
        }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.
                getForObject(FAKESTORE_API_BASE_URL, FakeStoreProductDto[].class);
        if(null == fakeStoreProductDtos){
            return null;
        }
        return Arrays.stream(fakeStoreProductDtos).map(FakeStoreProductDto::toProduct).toList();
    }
}
