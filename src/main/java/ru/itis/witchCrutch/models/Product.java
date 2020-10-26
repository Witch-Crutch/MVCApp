package ru.itis.witchCrutch.models;

import lombok.*;

@Data
@Builder
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private Category category;
    @Builder.Default
    private String image = ".";
    @Builder.Default
    private int popularity = 0;
}
