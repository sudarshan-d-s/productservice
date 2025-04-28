package com.sud.productservice.dtos;

import com.sud.productservice.models.Category;
import com.sud.productservice.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductRequestDto {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

    public Product toProduct(){
        return Product.builder().id(this.getId()).title(this.getTitle()).
                description(this.getDescription()).category(Category.builder().name(this.getCategoryName()).build())
                .price(this.getPrice()).imageUrl(this.getImageUrl()).build();
    }

    public static ProductRequestDto fromProduct(Product product){
        return ProductRequestDto.builder().id(product.getId()).title(product.getTitle())
                .description(product.getDescription()).categoryName(product.getCategory().getName())
                .price(product.getPrice()).imageUrl(product.getImageUrl()).build();
    }

}
