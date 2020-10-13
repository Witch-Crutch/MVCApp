package ru.itis.witchCrutch.services;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.repositories.PurchaseRepository;

import java.util.List;

public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Product> getUserPurchase(Basket basket) {
        return purchaseRepository.userPurchase(basket);
    }
}
