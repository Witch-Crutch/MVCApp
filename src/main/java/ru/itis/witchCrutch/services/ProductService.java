package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByName(String name);
    List<Product> getProductsByNameOrderByPrice(String name);
    List<Product> getProductsByNameOrderByPopular(String name);
    List<Product> getAllProducts();
    Product getProductById(int id);
    List<Product> getProductsByCategory(Category category);
}
