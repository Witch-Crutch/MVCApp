package ru.itis.witchCrutch.jdbc.repositories;

import java.util.List;

public interface CrudRepositories<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T entity);

    void update(T entity);
}
