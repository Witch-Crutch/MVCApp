package ru.itis.witchCrutch.services.interfaces;

import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.Purchase;
import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface PurchaseService {
    List<Product> getUserPurchase(User user);

    void addPurchase(Purchase purchase);
}
