package ru.itis.witchCrutch.repositories.interfaces;

import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.Purchase;
import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface PurchaseRepository {
    void save(Purchase purchase);

    List<Product> userPurchase(User user);
}
