package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.entity.Purchase;

public interface IPurchaseService {
    List<Purchase> listPurchases();
    Purchase createPurchase(Purchase purchase);
    void updatePurchase(Purchase purchase);
    void deletePurchase(Integer id);
}
