package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;

import java.util.List;

public interface PurchaseService {
    List<Product> getUserPurchase(Basket basket);
}
