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
@Table(name = "Unidad", schema = "Inventario")
@AllArgsConstructor
@NoArgsConstructor
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Integer unitId;

    @Column(name = "id_producto", nullable = false)
    private Integer productId;

    @Column(name = "id_almacen", nullable = false)
    private Integer warehouseId;

    @Column(name = "serie", nullable = false, unique = true)
    private String series;

    @Column(name = "estado", length = 50)
    private String status;
}
