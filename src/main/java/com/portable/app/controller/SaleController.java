package com.portable.app.controller;

import com.portable.app.entity.Sale;
import com.portable.app.service.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/sales")
public class SaleController {

    @Autowired
    private SaleServiceImpl saleService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.listSales());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Sale> getSaleById(@PathVariable Integer id) {
        Sale sale = saleService.findSaleById(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        try {
            Sale createdSale = saleService.createSale(sale);
            return ResponseEntity.ok(createdSale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Void> updateSale(@PathVariable Integer id, @RequestBody Sale sale) {
        try {
            sale.setSaleId(id);
            saleService.updateSale(sale);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENTAS')")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer id) {
        try {
            saleService.deleteSale(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}