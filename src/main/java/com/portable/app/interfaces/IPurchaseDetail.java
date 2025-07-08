package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.PurchaseDetailDto;
import com.portable.app.entity.PurchaseDetail;

public interface IPurchaseDetail {
    List<PurchaseDetail> listPurchaseDetails();
    PurchaseDetailDto createPurchaseDetail(PurchaseDetailDto purchaseDetailDto);
    void updatePurchaseDetail(PurchaseDetailDto purchaseDetailDto);
    void deletePurchaseDetail(Integer purchaseDetailId);
}
