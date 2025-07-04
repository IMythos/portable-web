package com.portable.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Almacen", schema = "Inventario")
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private Integer warehouseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Branch branch;

    @Column(name = "cod_alm", length = 4, nullable = false)
    private String warehouseCode;

    @Column(name = "nom_alm", length = 50, nullable = false)
    private String warehouseName;
}
