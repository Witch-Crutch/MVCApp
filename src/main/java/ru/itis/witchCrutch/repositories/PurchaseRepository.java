package ru.itis.witchCrutch.repositories;

import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.Purchase;
import ru.itis.witchCrutch.models.User;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase> {
    List<Product> userPurchase(User user);
}
