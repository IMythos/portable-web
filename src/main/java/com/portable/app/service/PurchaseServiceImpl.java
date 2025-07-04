package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.entity.Purchase;
import com.portable.app.interfaces.IPurchaseService;
import com.portable.app.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements IPurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Purchase> listPurchases() {
        return purchaseRepository.listPurchases();
    }

    @Override
    @Transactional
    public Purchase createPurchase(Purchase purchase) {
        Integer newId = purchaseRepository.insertPurchase(
            purchase.getUser().getUserId(),
            purchase.getProvider().getProviderId(),
            purchase.getDate(),
            purchase.getTotalAmount()
        );

        purchase.setPurchaseId(newId);
        return purchase;
    }

    @Override
    @Transactional
    public void updatePurchase(Purchase purchase) {
        purchaseRepository.updatePurchase(
            purchase.getPurchaseId(),
            purchase.getUser().getUserId(),
            purchase.getProvider().getProviderId(),
            purchase.getDate(),
            purchase.getTotalAmount()
        );
    }

    @Override
    @Transactional
    public void deletePurchase(Integer id) {
        purchaseRepository.deletePurchase(id);
    }

}
