package ru.itis.witchCrutch.models;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Basket {
    private int id;
    private List<Product> products;
    private User user;
}
