package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.User;

import javax.sql.DataSource;
import java.util.List;

public class ProductRepositoryJdbcImpl implements ProductRepository {

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT p.id, p.name, p.description, p.price, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id";

    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT p.id, p.name, p.description, p.price, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id where p.id=?";

    //language=SQL
    private final String SQL_FIND_BY_NAME = "SELECT p.id, p.name, p.description, p.price, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id where p.name LIKE ?";

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;

    public ProductRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    public RowMapper<Product> ProductRowMapper = row -> Product.builder()
            .id(row.getInt("id"))
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
        return template.query(SQL_FIND_ALL, ProductRowMapper);
    }

    @Override
    public Product getProductById(int id) {
        List<Product> products = template.query(SQL_FIND_BY_ID, ProductRowMapper, id);
        return !products.isEmpty() ? products.get(0) : null;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        name = "%" + name + "%";
        List<Product> products = template.query(SQL_FIND_BY_NAME, ProductRowMapper, name);
        return !products.isEmpty() ? products : null;
    }
}
