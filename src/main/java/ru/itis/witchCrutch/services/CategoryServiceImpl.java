package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.repositories.interfaces.CategoryRepository;
import ru.itis.witchCrutch.services.interfaces.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getAll();
    }
}
