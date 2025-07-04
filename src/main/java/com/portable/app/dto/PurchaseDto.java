package com.portable.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {
    private Integer purchaseId;
    private Integer userId;
    private Integer providerId;
    private LocalDate purchaseDate;
    private Double totalAmount;
}
