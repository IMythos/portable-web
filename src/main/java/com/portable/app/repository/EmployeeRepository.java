package com.portable.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Procedure(procedureName = "Administracion.usp_ListarEmpleadosRol")
    List<Object[]> listEmployeesForSP();

    @Query(value = "EXEC Administracion.usp_InsertarEmpleado :nom_emp, :ape_mat, :ape_pat, :dni_emp, :dir_emp, :tel_emp, :email, :sex_emp, :fec_nac, :fec_ing", nativeQuery = true)
    Integer insertEmployee(
        @Param("nom_emp") String nomEmpleado,
        @Param("ape_mat") String apeMatEmpleado,
        @Param("ape_pat") String apePatEmpleado,
        @Param("dni_emp") String dniEmpleado,
        @Param("dir_emp") String dirEmpleado,
        @Param("tel_emp") String telEmpleado,
        @Param("email") String emailEmpleado,
        @Param("sex_emp") Character sexoEmpleado,
        @Param("fec_nac") LocalDate fecNacEmpleado,
        @Param("fec_ing") LocalDate fecIngEmpleado
    );

    @Modifying
    @Query(value = "EXEC Administracion.usp_ActualizarEmpleado :id_empleado, :nom_emp, :ape_mat, :ape_pat, :dni_emp, :dir_emp, :tel_emp, :email, :sex_emp, :fec_nac, :fec_ing", nativeQuery = true)
    void updateEmployee(
        @Param("id_empleado") Integer idEmpleado,
        @Param("nom_emp") String nomEmpleado,
        @Param("ape_mat") String apeMatEmpleado,
        @Param("ape_pat") String apePatEmpleado,
        @Param("dni_emp") String dniEmpleado,
        @Param("dir_emp") String dirEmpleado,
        @Param("tel_emp") String telEmpleado,
        @Param("email") String emailEmpleado, 
        @Param("sex_emp") Character sexoEmpleado,
        @Param("fec_nac") LocalDate fecNacEmpleado,
        @Param("fec_ing") LocalDate fecIngEmpleado
    );

    @Procedure(procedureName = "Administracion.usp_BorrarEmpleado")
    void deleteEmployee(Integer idEmpleado);

    @Query(value = "EXEC Administracion.usp_TotalEmpleados", nativeQuery = true)
    Integer getTotalEmployees();

    @Query(value = "EXEC Administracion.usp_EmpleadosPorAnioIngreso", nativeQuery = true)
    List<Object[]> getEmployeesByYearOfJoining();

    @Query(value = "EXEC Administracion.usp_TotalEmpleadosConUsuario", nativeQuery = true)
    Integer getTotalUsers();

    Optional<Employee> findById(Integer employeeId);
}
