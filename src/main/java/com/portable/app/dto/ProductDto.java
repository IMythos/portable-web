package com.portable.app.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer productTd;
    private String productCode; 
    private String anexedCode;
    private String description;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private BigDecimal wholeSale;
    private String category;
    private Boolean status;
    private String brand;
}
