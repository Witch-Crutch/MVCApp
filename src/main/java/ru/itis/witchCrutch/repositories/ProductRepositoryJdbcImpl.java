package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.models.Product;

import javax.sql.DataSource;
import java.util.List;

public class ProductRepositoryJdbcImpl implements ProductRepository{

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT p.name, p.description, p.price, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id";

    private final DataSource dataSource;

    public ProductRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public RowMapper<Product> ProductRowMapper = row -> Product.builder()
            .name(row.getString("name"))
            .description(row.getString("description"))
            .price(row.getInt("price"))
            .image(row.getString("image"))
            .category(Category.builder().name(row.getString("ca_name")).build())
            .build();

    @Override
    public void save(Product entity) {

    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public List<Product> findAll() {
        SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
        return template.query(SQL_FIND_ALL, ProductRowMapper);
    }
}
