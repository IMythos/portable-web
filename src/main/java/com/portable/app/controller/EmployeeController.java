package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.entity.Employee;
import com.portable.app.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/v1/api")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeServiceImpl.listEmployees();
    }
}
