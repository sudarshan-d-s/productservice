package com.sud.productservice.dtos;

import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
public class FakeStoreProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;


    public Product toProduct(){
        Product product = new Product();
        Category cat = new Category();
        cat.setName(category);
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(cat);
        product.setPrice(price);
        product.setImageUrl(image);
        return product;
    }

    public static FakeStoreProductResponseDto fromProduct(Product product){
        if(null == product){
            return null;
        }
        return FakeStoreProductResponseDto.builder().id(product.getId()).title(product.getTitle())
                .description(product.getDescription()).category(Optional.ofNullable(product.
                        getCategory()).map(Category::getName).orElseGet(() -> null))
                .price(product.getPrice()).image(product.getImageUrl()).build();
    }
}
