package com.portable.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer employeeId; // id_empleado
    private Integer roleId; // id_rol
    private String employeeRoleName; // nombre_rol
    private String employeeName; // nom_emp
    private String employeeMaternalSurname; // ape_mat
    private String employeePaternalSurname; // ape_pat
    private String employeeDni; // dni_emp
    private String employeeAddress; // dir_emp
    private String employeePhoneNumber; // tel_emp
    private String employeeEmail; // email
    private Character employeeSex; // sex_emp
    private LocalDate employeeBirthDate; // fec_nac
    private LocalDate employeeEntryDate; // fec_ing
}
