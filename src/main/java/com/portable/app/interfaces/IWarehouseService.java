package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.WarehouseResponseDto;
import com.portable.app.entity.Warehouse;

public interface IWarehouseService {
    List<WarehouseResponseDto> listWarehouses();
    List<Warehouse> listAllWarehouses();
    Warehouse createWarehouse(Warehouse warehouse);
    void updateWarehouse(Warehouse warehouse);
    void deleteWarehouse(Integer warehouseId);
}
