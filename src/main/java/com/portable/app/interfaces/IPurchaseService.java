package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.PurchaseDto;
import com.portable.app.entity.Purchase;

public interface IPurchaseService {
    List<Purchase> listPurchases();
    PurchaseDto createPurchase(PurchaseDto purchaseDto);
    void updatePurchase(PurchaseDto purchaseDto);
    void deletePurchase(Integer purchaseId);
}
