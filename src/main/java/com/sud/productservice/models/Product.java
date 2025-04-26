package com.sud.productservice.models;

import lombok.*;

@Builder
@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
