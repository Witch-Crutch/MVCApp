package ru.itis.witchCrutch.models;

import lombok.*;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private Category category;
    @Builder.Default
    private String image = ".";
}
