package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product> {
    Product getProductById(int id);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByNameOrderByPrice(String name);
    List<Product> getProductsByNameOrderByPopular(String name);
}
