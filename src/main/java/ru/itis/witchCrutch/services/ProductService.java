package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductByName(String name);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Category category);
}
