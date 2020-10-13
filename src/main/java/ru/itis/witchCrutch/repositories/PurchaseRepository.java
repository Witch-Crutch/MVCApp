package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;

import java.util.List;

public interface PurchaseRepository {
    List<Product> userPurchase(Basket basket);
}
