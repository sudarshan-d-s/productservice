package com.sud.productservice.dtos;

import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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

    public Product toProduct(){
        Product product = new Product();
        Category cat = new Category();
        cat.setName(categoryName);
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(cat);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        return product;
    }

    public static ProductResponseDto fromProduct(Product product){
        if(null == product){
            return null;
        }
        return ProductResponseDto.builder().id(product.getId()).title(product.getTitle())
                .description(product.getDescription()).categoryName(Optional.ofNullable(product.
                        getCategory()).map(Category::getName).orElseGet(() -> null))
                .price(product.getPrice()).imageUrl(product.getImageUrl()).build();
    }

}
