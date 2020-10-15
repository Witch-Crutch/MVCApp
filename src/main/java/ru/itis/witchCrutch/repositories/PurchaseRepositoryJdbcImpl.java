package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.Purchase;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.BasketService;
import ru.itis.witchCrutch.services.ProductService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static ru.itis.witchCrutch.repositories.ProductRepositoryJdbcImpl.ProductRowMapper;

public class PurchaseRepositoryJdbcImpl implements PurchaseRepository{

    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;
    private final BasketService basketService;
    private final ProductService productService;

    //language=SQL
    private static final String SQL_FIND = "SELECT p.id, p.popularity, p.name, p.description, p.price, p.image, c.name as ca_name FROM purchase pu inner join customer_purchase cp on pu.id = cp.purchase_id inner join product p on p.id = cp.product_id inner join categories c on c.id = p.category_id where customer_id=?";

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO purchase (basket_id, date, customer_id) VALUES (?, 'today'::date, ?);";

    //language=SQL
    private static final String SQL_SAVE_PRODUCTS = "INSERT INTO customer_purchase (purchase_id, product_id) VALUES (?, ?);";

    //language=SQL
    private static final String SQL_FIND_ID = "SELECT id from purchase where basket_id=? ORDER BY id DESC;";

    private final RowMapper<Purchase> purchaseRowMapper = row -> Purchase.builder()
            .id(row.getInt("id")).build();

    public PurchaseRepositoryJdbcImpl(DataSource dataSource, BasketService basketService, ProductService productService) {
        this.dataSource = dataSource;
        this.basketService = basketService;
        this.productService = productService;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public List<Product> userPurchase(User user) {
        List<Product> products = template.query(SQL_FIND, ProductRowMapper, user.getId());
        return !products.isEmpty() ? products : new ArrayList<>();
    }

    @Override
    public void save(Purchase entity) {
        template.update(SQL_SAVE, entity.getBasketId(), entity.getCustomer().getId());
        Purchase purchase = template.query(SQL_FIND_ID, purchaseRowMapper, entity.getBasketId()).get(0);
        for (Product product : entity.getProducts()) {
            product.setPopularity(product.getPopularity() + 1);
            template.update(SQL_SAVE_PRODUCTS, purchase.getId(), product.getId());
            basketService.deleteProductFromBasket(Basket.builder().id(entity.getBasketId()).build(), product);
            productService.updateProduct(product);
        }
    }

    @Override
    public void update(Purchase entity) {

    }

    @Override
    public void delete(Purchase entity) {

    }

    @Override
    public List<Purchase> findAll() {
        return null;
    }
}
