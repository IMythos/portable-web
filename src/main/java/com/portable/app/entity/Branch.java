package com.portable.app.entity;

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
@Data
@Table(name = "Sucursal", schema = "Inventario")
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer branchId;

    @Column(name = "nom_suc", length = 50, nullable = false)
    private String branchName;

    @Column(name = "distrito", length = 100, nullable = false)
    private String district;

    @Column(name = "direccion", length = 200, nullable = false)
    private String address;
}
