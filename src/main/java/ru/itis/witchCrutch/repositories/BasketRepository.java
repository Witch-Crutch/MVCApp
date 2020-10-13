package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.User;

public interface BasketRepository extends CrudRepository<Basket>{
    Basket getUserBasket(User user);
}
