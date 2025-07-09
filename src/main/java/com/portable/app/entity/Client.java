package com.portable.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cliente", schema = "Ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @Column(name = "id_cliente")
    private Integer clientId;

    @Column(name = "nombre", length = 100, nullable = false)
    private String firstname;

    @Column(name = "ape_pat", length = 50, nullable = false)
    private String paternalLastname;

    @Column(name = "ape_mat", length = 50, nullable = false)
    private String maternalLastname;

    @Column(name = "ruc_dni", length = 12, nullable = false)
    private String rucDni;

    @Column(name = "direccion", length = 200)
    private String address;

    @Column(name = "telefono", length = 9)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "fec_reg")
    private LocalDate registerDate;
}
