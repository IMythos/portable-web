package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.entity.Product;
import com.portable.app.service.ProductServiceImpl;

@RestController
@RequestMapping("/v1/api/inventory")
public class InventoryController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    // ====== USUARIO INVENTARIO ======
    // [GET] -> Obtiene lista de productos general
    @PreAuthorize("hasRole('INVENTARIO')")
    @GetMapping("/products")
    public List<Product> getProductList() {
        return productServiceImpl.listProducts();
    }
}
