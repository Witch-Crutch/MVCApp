package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.User;

public interface BasketService {
    void createBasket(Basket basket);
    Basket getUserBasket(User user);
    void addProductInBasket(Product product);
    void deleteProductFromBasket(Product product);
}
