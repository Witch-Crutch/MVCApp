package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.repositories.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsByName(String name) { return productRepository.getProductsByName(name); }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) { return productRepository.getProductById(id); }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return null;
    }
}
