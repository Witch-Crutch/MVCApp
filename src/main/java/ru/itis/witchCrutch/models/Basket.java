package ru.itis.witchCrutch.models;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Basket {
    private int id;
    private List<Product> products;
    private User user;
}
