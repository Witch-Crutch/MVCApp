package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.UsersService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class BasketRepositoryJdbcImpl implements BasketRepository{

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;
    private UsersService usersService;

    //language=SQL
    private static final String SQL_CREATE = "INSERT INTO basket (customer_id) values (?);";

    //language=SQL
    private static final String SQL_ADD_PRODUCT = "INSERT INTO customer_basket (basket_id, product_id) VALUES (?, ?);";

    //language=SQL
    private static final String SQL_FIND_BY_USER_ID =
            "SELECT * FROM basket where customer_id=?";

    private RowMapper<Basket> BasketRowMapper = row -> Basket.builder()
            .id(row.getInt("id"))
            .user(usersService.getUserById(row.getInt("customer_id")))
            .products(new ArrayList<>())
            .build();

    public BasketRepositoryJdbcImpl(DataSource dataSource, UsersService userService) {
        this.dataSource = dataSource;
        this.usersService = userService;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public void save(Basket basket) {
        template.update(SQL_CREATE, basket.getUser().getId());
    }

    @Override
    public void update(Basket entity) {

    }

    @Override
    public void delete(Basket entity) {

    }

    @Override
    public List<Basket> findAll() {
        return null;
    }

    @Override
    public Basket getUserBasket(User user) {
        List<Basket> baskets = template.query(SQL_FIND_BY_USER_ID, BasketRowMapper, user.getId());
        return !baskets.isEmpty() ? baskets.get(0) : null;
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        template.update(SQL_ADD_PRODUCT, basket.getId(), product.getId());
    }
}
