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

import com.portable.app.entity.Purchase;
import com.portable.app.service.PurchaseServiceImpl;

@RestController
@RequestMapping("/v1/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseServiceImpl purchaseServiceImpl;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public List<Purchase> getPurchasesList() {
        return purchaseServiceImpl.listPurchases();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        try {
            Purchase createdPurchase = purchaseServiceImpl.createPurchase(purchase);
            return ResponseEntity.ok(createdPurchase);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public ResponseEntity<Void> updatePurchase(@PathVariable Integer id, @RequestBody Purchase purchase) {
        try {
            purchase.setPurchaseId(id);
            purchaseServiceImpl.updatePurchase(purchase);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'COMPRAS')")
    public ResponseEntity<Void> deletePurchase(@PathVariable Integer id) {
        try {
            purchaseServiceImpl.deletePurchase(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
