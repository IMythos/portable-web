package com.portable.app.controller;

import com.portable.app.entity.Receipt;
import com.portable.app.service.ReceiptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptServiceImpl receiptService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.listReceipts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Integer id) {
        Receipt receipt = receiptService.findReceiptById(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {
        try {
            Receipt createdReceipt = receiptService.createReceipt(receipt);
            return ResponseEntity.ok(createdReceipt);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Void> updateReceipt(@PathVariable Integer id, @RequestBody Receipt receipt) {
        try {
            receipt.setReceiptId(id);
            receiptService.updateReceipt(receipt);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Integer id) {
        try {
            receiptService.deleteReceipt(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}