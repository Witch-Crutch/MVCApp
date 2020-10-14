package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.Purchase;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.PurchaseRepository;

import java.util.List;

public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Product> getUserPurchase(User user) {
        return purchaseRepository.userPurchase(user);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }


}
