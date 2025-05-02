package com.sud.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

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
