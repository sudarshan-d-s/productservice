package com.sud.productservice.dtos;

import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;


    public Product toProduct(){
        return Product.builder().id(this.getId()).title(this.getTitle()).
                description(this.getDescription()).category(Category.builder().name(this.getCategory()).build())
                .price(this.getPrice()).imageUrl(this.getImage()).build();
    }

    public static FakeStoreProductDto fromProduct(Product product){
        return FakeStoreProductDto.builder().id(product.getId()).title(product.getTitle())
                .description(product.getDescription()).category(product.getCategory().getName())
                .price(product.getPrice()).image(product.getImageUrl()).build();
    }
}
