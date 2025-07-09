package com.portable.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Venta", schema = "Ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer saleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comprobante", nullable = false)
    private Receipt receipt;

    @Column(name = "tipo_pago", length = 20, nullable = false)
    private String paymentType;

    @Column(name = "tipo_ven")
    private Boolean saleType;

    @Column(name = "total", precision = 18, scale = 2, nullable = false)
    private BigDecimal total;
}