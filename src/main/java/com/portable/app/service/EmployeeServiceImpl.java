package com.portable.app.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portable.app.dto.EmployeeDto;
import com.portable.app.entity.Employee;
import com.portable.app.entity.User;
import com.portable.app.interfaces.IEmployeeService;
import com.portable.app.repository.EmployeeRepository;
import com.portable.app.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> listEmployees() {
        return employeeRepository.listEmployeesForSP()
                .stream()
                .map(employee -> {
                    System.out.println("Employee data: " + employee[11]);
                    return new EmployeeDto(
                            (Integer) employee[0], // employeeId
                            (Integer) employee[1], // roleId
                            (String) employee[2], // employeeRoleName
                            (String) employee[3], // employeeName
                            (String) employee[4], // employeeMaternalSurname
                            (String) employee[5], // employeePaternalSurname
                            (String) employee[6], // employeeDni
                            (String) employee[7], // employeeAddress
                            (String) employee[8], // employeePhoneNumber
                            (String) employee[9], // employeeEmail
                            (Character) employee[10], // employeeSex
                            employee[11] != null ? ((Date) employee[11]).toLocalDate() : null, // employeeBirthDate
                            employee[12] != null ? ((Date) employee[12]).toLocalDate() : null // employeeEntryDate
                    );
                })
                .toList();
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        Integer newId = employeeRepository.insertEmployee(
                employee.getEmployeeName(),
                employee.getMaternalSurname(),
                employee.getPaternalSurname(),
                employee.getDni(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getEmail(),
                employee.getSex(),
                employee.getBirthDate(),
                employee.getEntryDate());
        employee.setEmployeeId(newId);
        return employee;
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employeeRepository.updateEmployee(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getMaternalSurname(),
                employee.getPaternalSurname(),
                employee.getDni(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getEmail(),
                employee.getSex(),
                employee.getBirthDate(),
                employee.getEntryDate());
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalEmployees() {
        return employeeRepository.getTotalEmployees();
    }

    @Override
    public List<Map<String, Object>> getEmployeesByYearOfJoining() {
        List<Object[]> results = employeeRepository.getEmployeesByYearOfJoining();
        List<Map<String, Object>> data = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("year", row[0]);
            map.put("totalEmployees", row[1]);
            data.add(map);
        }

        return data;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalUsers() {
        return employeeRepository.getTotalUsers();
    }

    @Override
    public EmployeeDto findEmployeeDtoById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado."));

        // Busca el usuario asociado a este empleado
        User user = userRepository.findByEmployee_EmployeeId(employeeId).orElse(null);

        Integer roleId = null;
        String roleName = null;
        if (user != null && user.getRole() != null) {
            roleId = user.getRole().getIdRole();
            roleName = user.getRole().getRoleName();
        }

        return new EmployeeDto(
                employee.getEmployeeId(),
                roleId,
                roleName,
                employee.getEmployeeName(),
                employee.getMaternalSurname(),
                employee.getPaternalSurname(),
                employee.getDni(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getEmail(),
                employee.getSex(),
                employee.getBirthDate(),
                employee.getEntryDate());
    }
}
