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

import com.portable.app.dto.WarehouseResponseDto;
import com.portable.app.entity.Warehouse;
import com.portable.app.service.WarehouseServiceImpl;

@RestController
@RequestMapping("/v1/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseServiceImpl warehouseServiceImpl;

    @GetMapping("/listDto")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public List<WarehouseResponseDto> getWarehouses() {
        return warehouseServiceImpl.listWarehouses();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public List<Warehouse> getAllWarehouses() {
        return warehouseServiceImpl.listAllWarehouses();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        try {
            Warehouse createdWarehouse = warehouseServiceImpl.createWarehouse(warehouse);
            return ResponseEntity.ok(createdWarehouse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        try {
            warehouse.setWarehouseId(id);
            warehouseServiceImpl.updateWarehouse(warehouse);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Integer id) {
        try {
            warehouseServiceImpl.deleteWarehouse(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
