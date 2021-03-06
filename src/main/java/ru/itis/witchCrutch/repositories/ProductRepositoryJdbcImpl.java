package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Category;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.repositories.interfaces.ProductRepository;
import ru.itis.witchCrutch.repositories.interfaces.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class ProductRepositoryJdbcImpl implements ProductRepository {

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT p.id, p.name, p.popularity, p.description, p.price, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id";

    //language=SQL
    private final String SQL_UPDATE = "update product set popularity = ? where id=?";

    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT p.id, p.name, p.description, p.price, p.popularity, p.image, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id where p.id=?";

    //language=SQL
    private final String SQL_FIND_BY_NAME = "SELECT p.id, p.name, p.description, p.price, p.image, p.popularity, c.name ca_name FROM product p INNER JOIN categories c on c.id = p.category_id where p.name LIKE ?";

    //language=SQL
    private final String SQL_FIND_BY_NAME_ORDER_BY_PRICE = SQL_FIND_BY_NAME + " ORDER BY price;";

    //language=SQL
    private final String SQL_FIND_BY_NAME_ORDER_BY_POPULARITY = SQL_FIND_BY_NAME + " ORDER BY popularity DESC;";

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;

    public ProductRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    public static RowMapper<Product> ProductRowMapper = row -> Product.builder()
            .id(row.getInt("id"))
            .name(row.getString("name"))
            .description(row.getString("description"))
            .price(row.getInt("price"))
            .image(row.getString("image"))
            .category(Category.builder().name(row.getString("ca_name")).build())
            .popularity(row.getInt("popularity"))
            .build();


    @Override
    public void update(Product entity) {
        template.update(SQL_UPDATE, entity.getPopularity(), entity.getId());
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
        return orderProducts(name, SQL_FIND_BY_NAME);
    }

    @Override
    public List<Product> getProductsByNameOrderByPrice(String name) {
        return orderProducts(name, SQL_FIND_BY_NAME_ORDER_BY_PRICE);
    }

    @Override
    public List<Product> getProductsByNameOrderByPopular(String name) {
        return orderProducts(name, SQL_FIND_BY_NAME_ORDER_BY_POPULARITY);
    }

    private List<Product> orderProducts(String name, String SQL) {
        name = "%" + name + "%";
        List<Product> products = template.query(SQL, ProductRowMapper, name);
        return !products.isEmpty() ? products : null;
    }
}
