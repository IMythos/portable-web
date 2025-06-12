package com.portable.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empleado", schema = "Administracion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer employeeId;

    @Column(name = "nom_emp", length = 50, nullable = false)
    private String employeeName;

    @Column(name = "ape_mat", length = 50, nullable = false)
    private String maternalSurname;

    @Column(name = "ape_pat", length = 50, nullable = false)
    private String paternalSurname;

    @Column(name = "dni_emp", length = 8, nullable = false, unique = true)
    private String dni;

    @Column(name = "dir_emp", length = 200, nullable = false)
    private String address;

    @Column(name = "tel_emp", length = 9, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "sex_emp", length = 1)
    private Character sex;

    @Column(name = "fec_nac", nullable = false)
    private LocalDate birthDate;

    @Column(name = "fec_ing", nullable = false)
    private LocalDate entryDate;
}
