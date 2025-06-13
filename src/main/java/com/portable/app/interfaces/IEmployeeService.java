package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.EmployeeDto;

public interface IEmployeeService {
    List<EmployeeDto> listEmployees();
}
