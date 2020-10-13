package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static ru.itis.witchCrutch.repositories.ProductRepositoryJdbcImpl.ProductRowMapper;

public class PurchaseRepositoryJdbcImpl implements PurchaseRepository{

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;

    //language=SQL
    private static final String SQL_FIND = "SELECT p.id, p.name, p.description, p.price, p.image, c.name as ca_name FROM purchase pu inner join customer_basket cb on pu.basket_id = cb.basket_id inner join product p on p.id = cb.product_id inner join categories c on c.id = p.category_id where cb.basket_id=?;";

    public PurchaseRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public List<Product> userPurchase(Basket basket) {
        List<Product> products = template.query(SQL_FIND, ProductRowMapper, basket.getId());
        return !products.isEmpty() ? products : new ArrayList<>();
    }
}
