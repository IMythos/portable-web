package com.portable.app.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portable.app.dto.EmployeeDto;
import com.portable.app.interfaces.IEmployeeService;
import com.portable.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

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
}
