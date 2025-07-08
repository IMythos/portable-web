package com.portable.app.interfaces;

import java.util.List;
import java.util.Map;

import com.portable.app.dto.EmployeeDto;
import com.portable.app.entity.Employee;

public interface IEmployeeService {
    List<EmployeeDto> listEmployees();
    Employee createEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
    Integer getTotalEmployees();
    List<Map<String, Object>> getEmployeesByYearOfJoining();
    Integer getTotalUsers();
    EmployeeDto findEmployeeDtoById(Integer employeeId);
}
