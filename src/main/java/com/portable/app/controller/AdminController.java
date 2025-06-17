package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.dto.EmployeeDto;
import com.portable.app.dto.UnitBrandDto;
import com.portable.app.entity.Product;
import com.portable.app.service.EmployeeServiceImpl;
import com.portable.app.service.ProductServiceImpl;
import com.portable.app.service.UnitServiceImpl;

@RestController
@RequestMapping("/v1/api/admin")
public class AdminController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private UnitServiceImpl unitServiceImpl;

    // ===== USUARIO ADMINISTRADOR =====
    // Obtiene sus propios datos de usuario
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return "Usuario autenticado: " + userDetails.getUsername();
    }

    // ====== USUARIO ADMINISTRADOR (EMPLEADO) ======
    // [GET] -> Obtener lista de empleados filtrados con rol
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/employees")
    public List<EmployeeDto> getEmployeeListDto() {
        return employeeServiceImpl.listEmployees();
    }

    // ====== USUARIO ADMINISTRADOR (INVENTARIO) ======
    // [GET] -> Obtiene lista de productos general
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/products")
    public List<Product> getProductsListDto() {
        return productServiceImpl.listProducts();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/top-brands")
    public List<UnitBrandDto> getTopBrands() {
        return unitServiceImpl.listUnitsPerBrand();
    }
}
