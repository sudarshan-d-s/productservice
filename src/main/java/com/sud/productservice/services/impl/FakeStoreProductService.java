package com.sud.productservice.services.impl;

import com.sud.productservice.dtos.FakeStoreProductRequestDto;
import com.sud.productservice.dtos.FakeStoreProductResponseDto;
import com.sud.productservice.models.Product;
import com.sud.productservice.services.ProductService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        FakeStoreProductResponseDto fakeStoreProductResponseDto = restTemplate.
                getForObject(FAKESTORE_API_BASE_URL+productId, FakeStoreProductResponseDto.class);
        if(null == fakeStoreProductResponseDto){
            return null;
        }
        return fakeStoreProductResponseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        ResponseEntity<List<FakeStoreProductResponseDto>> response = restTemplate.exchange(FAKESTORE_API_BASE_URL,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        List<FakeStoreProductResponseDto> list = response.getBody();
        if(list == null){
            return null;
        }
        return list.stream().map(FakeStoreProductResponseDto::toProduct).toList();
    }

    @Override
    public Product createProduct(Product product) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreProductRequestDto> requestEntity = new HttpEntity<>(
                FakeStoreProductRequestDto.fromProduct(product), headers);

        ResponseEntity<FakeStoreProductResponseDto> response = restTemplate.exchange(FAKESTORE_API_BASE_URL,
                HttpMethod.POST, requestEntity,
                FakeStoreProductResponseDto.class);
        if(response.getBody() == null){
            return null;
        }
        return response.getBody().toProduct();
    }

    @Override
    public Product updateProduct(Product product) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreProductRequestDto> requestEntity = new HttpEntity<>(
                FakeStoreProductRequestDto.fromProduct(product), headers);

        ResponseEntity<FakeStoreProductResponseDto> response = restTemplate.exchange(FAKESTORE_API_BASE_URL+product.getId(),
                HttpMethod.PUT, requestEntity,
                FakeStoreProductResponseDto.class);
        if(response.getBody() == null){
            return null;
        }
        return response.getBody().toProduct();


    }

    @Override
    public void deleteProduct(Long productId) {
        ResponseEntity<FakeStoreProductResponseDto> response = restTemplate.exchange(FAKESTORE_API_BASE_URL + productId,
                HttpMethod.DELETE, null,
                FakeStoreProductResponseDto.class);
    }
}
