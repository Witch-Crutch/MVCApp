package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.dataSource.SimpleDataSource;
import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.repositories.interfaces.CategoryRepository;
import ru.itis.witchCrutch.repositories.interfaces.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class CategoryRepositoryJdbcImpl implements CategoryRepository {

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;

    //language=SQL
    private final static String SQL_GET_ALL = "SELECT * FROM categories";

    private final RowMapper<Category> categoryRowMapper = row -> Category.builder()
            .name(row.getString("name"))
            .image(row.getString("image"))
            .build();

    public CategoryRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = template.query(SQL_GET_ALL, categoryRowMapper);
        return categories.isEmpty() ? null : categories;
    }
}
