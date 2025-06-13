package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.dto.EmployeeDto;
import com.portable.app.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/v1/api/admin")
public class AdminController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    // Obtiene sus propios datos de usuario
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return "Usuario autenticado: " + userDetails.getUsername();
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getEmployeeListDto() {
        return employeeServiceImpl.listEmployees();
    }
}
