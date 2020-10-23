package ru.itis.witchCrutch.repositories.interfaces;

import ru.itis.witchCrutch.models.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAll();
}
