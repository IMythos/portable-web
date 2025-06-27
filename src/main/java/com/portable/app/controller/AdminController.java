package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.dto.EmployeeDto;
import com.portable.app.dto.UnitBrandDto;
import com.portable.app.dto.WarehouseResponseDto;
import com.portable.app.entity.Product;
import com.portable.app.service.EmployeeServiceImpl;
import com.portable.app.service.ProductServiceImpl;
import com.portable.app.service.UnitServiceImpl;
import com.portable.app.service.WarehouseServiceImpl;

@RestController
@RequestMapping("/v1/api/admin")
public class AdminController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private UnitServiceImpl unitServiceImpl;

    @Autowired
    private WarehouseServiceImpl warehouseServiceImpl;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return "Usuario autenticado: " + userDetails.getUsername();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/employees")
    public List<EmployeeDto> getEmployeeListDto() {
        return employeeServiceImpl.listEmployees();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/products")
    public List<Product> getProductsListDto() {
        return productServiceImpl.listProducts();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/top/brands")
    public List<UnitBrandDto> getTopBrands() {
        return unitServiceImpl.listUnitsPerBrand();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/warehouses")
    public List<WarehouseResponseDto> getWarehouses() {
        return warehouseServiceImpl.listWarehouses();
    }

    @PostMapping("/product/create")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct =  productServiceImpl.createProduct(product);
            return ResponseEntity.ok(createdProduct);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/product/update/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        try {
            product.setProductId(id);
            productServiceImpl.updateProduct(product);

            return ResponseEntity.ok().build();
        } catch(Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/product/delete/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productServiceImpl.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }
}
