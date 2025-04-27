package com.sud.productservice.services.impl;

import com.sud.productservice.dtos.FakeStoreProductDto;
import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import com.sud.productservice.services.ProductService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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

        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(FAKESTORE_API_BASE_URL,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        List<FakeStoreProductDto> list = response.getBody();
        if(list == null){
            return null;
        }
        return list.stream().map(FakeStoreProductDto::toProduct).toList();
    }

    @Override
    public FakeStoreProductDto createProduct(Product product) {
        return restTemplate.postForObject(FAKESTORE_API_BASE_URL,
                FakeStoreProductDto.fromProduct(product), FakeStoreProductDto.class);
    }

    @Override
    public void updateProduct(Product product) {
        restTemplate.put(FAKESTORE_API_BASE_URL+product.getId(), FakeStoreProductDto.fromProduct(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        restTemplate.delete(FAKESTORE_API_BASE_URL + productId);
    }
}
