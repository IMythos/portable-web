package com.portable.app.controller;

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

import com.portable.app.dto.EmployeeDto;
import com.portable.app.entity.Employee;
import com.portable.app.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public List<EmployeeDto> getEmployeeListDto() {
        return employeeServiceImpl.listEmployees();
    }

    @GetMapping("/total-employees")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Integer> getTotalEmployees() {
        try {
            Integer total = employeeServiceImpl.getTotalEmployees();
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/total-users")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Integer> getTotalUsersWithEmployees() {
        try {
            Integer total = employeeServiceImpl.getTotalUsers();
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/employees-by-year")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<List<Map<String, Object>>> getEmployeesByYearOfJoining() {
        try {
            List<Map<String, Object>> employeesByYear = employeeServiceImpl.getEmployeesByYearOfJoining();
            return ResponseEntity.ok(employeesByYear);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        try {
            EmployeeDto employeeDto = employeeServiceImpl.findEmployeeDtoById(id);
            return ResponseEntity.ok(employeeDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = employeeServiceImpl.createEmployee(employee);
            return ResponseEntity.ok(createdEmployee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            employee.setEmployeeId(id);
            employeeServiceImpl.updateEmployee(employee);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeServiceImpl.deleteEmployee(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }    
}
