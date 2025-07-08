package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.dto.PurchaseDetailDto;
import com.portable.app.entity.PurchaseDetail;
import com.portable.app.service.PurchaseDetailServiceImpl;

@RestController
@RequestMapping("/v1/api/purchase-details")
public class PurchaseDetailController {

    @Autowired
    private PurchaseDetailServiceImpl purchaseDetailServiceImpl;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public List<PurchaseDetail> getPurchaseDetailsList() {
        return purchaseDetailServiceImpl.listPurchaseDetails();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public ResponseEntity<PurchaseDetailDto> createPurchaseDetail(@RequestBody PurchaseDetailDto purchaseDetailDto) {
        try {
            PurchaseDetailDto createPurchaseDetail  = purchaseDetailServiceImpl.createPurchaseDetail(purchaseDetailDto);
            return ResponseEntity.ok(createPurchaseDetail);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public ResponseEntity<Void> updatePurchaseDetail(@PathVariable Integer id, @RequestBody PurchaseDetailDto purchaseDetailDto) {
        try {
            purchaseDetailDto.setPurchaseDetailId(id);
            purchaseDetailServiceImpl.updatePurchaseDetail(purchaseDetailDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public ResponseEntity<Void> deletePurchaseDetail(@PathVariable Integer id) {
        try {
            purchaseDetailServiceImpl.deletePurchaseDetail(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
