package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.EmployeeDto;
import com.portable.app.entity.Employee;

public interface IEmployeeService {
    List<EmployeeDto> listEmployees();
    Employee createEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
}
