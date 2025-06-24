package com.portable.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponseDto {
    private Integer warehouseId; // id_almacen
    private String warehouseCode; // cod_alm
    private String warehouseName; // nom_alm
}
