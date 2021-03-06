package ru.itis.witchCrutch.services.interfaces;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.User;

public interface BasketService {
    void createBasket(Basket basket);

    Basket getUserBasket(User user);

    void addProductInBasket(Basket basket, Product product);

    void deleteProductFromBasket(Basket basket, Product product);
}
