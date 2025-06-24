package com.portable.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Almacen", schema = "Inventario")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private Integer warehouseId;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Branch branch;

    @Column(name = "cod_alm", length = 4, nullable = false)
    private String warehouseCode;

    @Column(name = "nom_alm", length = 50, nullable = false)
    private String warehouseName;
}
