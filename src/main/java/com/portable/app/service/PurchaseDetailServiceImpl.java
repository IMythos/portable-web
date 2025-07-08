package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.dto.PurchaseDetailDto;
import com.portable.app.entity.PurchaseDetail;
import com.portable.app.interfaces.IPurchaseDetail;
import com.portable.app.repository.PurchaseDetailRepository;

@Service
public class PurchaseDetailServiceImpl implements IPurchaseDetail {

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseDetail> listPurchaseDetails() {
        return purchaseDetailRepository.listPurchaseDetails();
    }

    @Override
    @Transactional
    public PurchaseDetailDto createPurchaseDetail(PurchaseDetailDto purchaseDetailDto) {
        Integer newId = purchaseDetailRepository.createPurchaseDetail(
            purchaseDetailDto.getPurchaseId(),
            purchaseDetailDto.getProductId(),
            purchaseDetailDto.getQuantity(),
            purchaseDetailDto.getUnitPrice()
        );

        purchaseDetailDto.setPurchaseDetailId(newId);
        return purchaseDetailDto;
    }

    @Override
    @Transactional
    public void updatePurchaseDetail(PurchaseDetailDto purchaseDetailDto) {
        purchaseDetailRepository.updatePurchaseDetail(
            purchaseDetailDto.getPurchaseDetailId(),
            purchaseDetailDto.getPurchaseId(),
            purchaseDetailDto.getProductId(),
            purchaseDetailDto.getQuantity(),
            purchaseDetailDto.getUnitPrice()
        );
    }

    @Override
    @Transactional
    public void deletePurchaseDetail(Integer purchaseDetailId) {
        purchaseDetailRepository.deletePurchaseDetail(purchaseDetailId);
    }
}
