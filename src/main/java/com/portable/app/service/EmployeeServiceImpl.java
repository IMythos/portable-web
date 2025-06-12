package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.entity.Employee;
import com.portable.app.interfaces.IEmployeeService;
import com.portable.app.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> listEmployees() {
        return employeeRepository.listEmployeesForSP();
    }
}
