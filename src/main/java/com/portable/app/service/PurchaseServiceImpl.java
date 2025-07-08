package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.dto.PurchaseDto;
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
    public PurchaseDto createPurchase(PurchaseDto purchaseDto) {
        Integer newId = purchaseRepository.insertPurchase(
            purchaseDto.getUserId(),
            purchaseDto.getProviderId(),
            purchaseDto.getPurchaseDate()
        );

        purchaseDto.setPurchaseId(newId);
        return purchaseDto;
    }

    @Override
    @Transactional
    public void updatePurchase(PurchaseDto purchaseDto) {
        purchaseRepository.updatePurchase(
            purchaseDto.getPurchaseId(),
            purchaseDto.getUserId(),
            purchaseDto.getProviderId(),
            purchaseDto.getPurchaseDate()
        );
    }

    @Override
    @Transactional
    public void deletePurchase(Integer id) {
        purchaseRepository.deletePurchase(id);
    }
}
