package com.sud.productservice.dtos;

import com.sud.productservice.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

    public static ProductResponseDto fromProduct(Product product){
        return ProductResponseDto.builder().id(product.getId()).title(product.getTitle())
                .description(product.getDescription()).categoryName(product.getCategory().getName())
                .price(product.getPrice()).imageUrl(product.getImageUrl()).build();
    }
}
