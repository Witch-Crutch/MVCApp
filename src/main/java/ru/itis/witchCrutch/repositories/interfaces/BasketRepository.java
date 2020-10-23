package ru.itis.witchCrutch.repositories.interfaces;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.User;

public interface BasketRepository extends CrudRepository<Basket>{
    Basket getUserBasket(User user);
    void addProduct(Basket basket, Product product);
    void deleteProduct(Basket basket, Product product);
}
