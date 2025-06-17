package com.portable.app.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Producto", schema = "Inventario")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "id_producto")
    private Integer productId;

    @Column(name = "cod_prod", length = 8, nullable = false)
    private String productCode;

    @Column(name = "cod_anexo", length = 8, nullable = false)
    private String productAnnex;

    @Column(name = "descripcion", length = 200, nullable = false)
    private String description;

    @Column(name = "pre_ven", nullable = false)
    private BigDecimal salePrice;

    @Column(name = "pre_com", nullable = false)
    private BigDecimal purchasePrice;

    @Column(name = "pre_may", nullable = false)
    private BigDecimal wholeSale;

    @Column(name = "categoria", length = 50)
    private String category;

    @Column(name = "estado")
    private Boolean status;

    @Column(name = "marca", length = 100)
    private String brand;
}
