package com.portable.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comprobante", schema = "Ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private Integer receiptId;

    @Column(name = "documento", nullable = false, length = 20)
    private String document;

    @Column(name = "serie", nullable = false, length = 4)
    private String series;

    @Column(name = "numero", nullable = false, length = 20)
    private String number;

    @Column(name = "fecha_emision")
    private LocalDateTime issueDate;

    @Column(name = "fecha_venc")
    private LocalDateTime dueDate;

    @Column(name = "moneda", nullable = false, length = 3)
    private String currency;
}