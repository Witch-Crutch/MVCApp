package ru.itis.witchCrutch.models;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Purchase {
    private int id;
    private int basketId;
    private User customer;
    private String date;
    private List<Product> products;
}
