package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.repositories.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductByName(String name) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return null;
    }
}
