package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.BasketRepository;

public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void createBasket(Basket basket) {
        basketRepository.save(basket);
    }

    @Override
    public Basket getUserBasket(User user) {
        return basketRepository.getUserBasket(user);
    }

    @Override
    public void addProductInBasket(Basket basket, Product product) {
        basketRepository.addProduct(basket, product);
    }

    @Override
    public void deleteProductFromBasket(Product product) {
    }
}
