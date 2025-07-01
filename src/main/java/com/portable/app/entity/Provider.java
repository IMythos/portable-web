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
@Table(name = "Proveedor", schema = "Compras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer providerId;

    @Column(name = "cod_prov", length = 8, nullable = false)
    private String providerCode;

    @Column(name = "nom_prov", length = 45, nullable = false)
    private String providerName;

    @Column(name = "ruc_prov", length = 12, nullable = false)
    private String providerRuc;
}
