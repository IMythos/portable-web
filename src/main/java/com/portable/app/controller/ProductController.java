package com.portable.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.portable.app.entity.Product;
import com.portable.app.service.ProductServiceImpl;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'INVENTARIO')")
    public List<Product> getProductsList() {
        return productServiceImpl.listProducts();
    }

    @GetMapping("/top-brands")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'INVENTARIO')")
    public List<Map<String, Object>> getTopBrands() {
        List<Object[]> topBrands = productServiceImpl.top5BrandsByProductCount();
        return topBrands.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("brand", obj[0]);
            map.put("count", obj[1]);
            return map;
        }).toList();
    }

    @GetMapping("/top-products")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'INVENTARIO')")
    public List<Map<String, Object>> getTopProducts() {
        List<Object[]> data = productServiceImpl.top5ProductsBySalePrice();
        return data.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("productAnnex", obj[0]);
            map.put("description", obj[1]);
            map.put("salePrice", obj[2]);
            return map;
        }).toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'INVENTARIO')")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        try {
            Product product = productServiceImpl.findProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'INVENTARIO')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productServiceImpl.createProduct(product);
            return ResponseEntity.ok(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'INVENTARIO')")
    public ResponseEntity<Void> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        try {
            product.setProductId(id);
            productServiceImpl.updateProduct(product);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'INVENTARIO')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productServiceImpl.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
