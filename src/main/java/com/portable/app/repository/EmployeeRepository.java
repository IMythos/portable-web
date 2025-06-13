package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

//import com.portable.app.dto.EmployeeDto;
import com.portable.app.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Procedure(procedureName = "Administracion.usp_ListarEmpleadosRol")
    List<Object[]> listEmployeesForSP();
}
