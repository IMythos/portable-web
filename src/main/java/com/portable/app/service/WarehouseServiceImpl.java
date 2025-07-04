package com.portable.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.dto.WarehouseResponseDto;
import com.portable.app.entity.Warehouse;
import com.portable.app.interfaces.IWarehouseService;
import com.portable.app.repository.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<WarehouseResponseDto> listWarehouses() {
        return warehouseRepository.getWarehouses()
                .stream()
                .map(warehouse -> new WarehouseResponseDto(
                        (Integer) warehouse[0], 
                        (String) warehouse[1], 
                        (String) warehouse[2]  
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Warehouse> listAllWarehouses() {
        return warehouseRepository.listWarehouses();
    }

    @Override
    @Transactional
    public Warehouse createWarehouse(Warehouse warehouse) {
        Integer newId = warehouseRepository.insertWarehouse(
            warehouse.getBranch().getBranchId(),
            warehouse.getWarehouseCode(),
            warehouse.getWarehouseName()
        );

        warehouse.setWarehouseId(newId);
        return warehouse;
    }

    @Override
    @Transactional
    public void updateWarehouse(Warehouse warehouse) {
        warehouseRepository.updateWarehouse(
            warehouse.getWarehouseId(),
            warehouse.getBranch().getBranchId(),
            warehouse.getWarehouseCode(),
            warehouse.getWarehouseName()
        );
    }

    @Override
    @Transactional
    public void deleteWarehouse(Integer warehouseId) {
        warehouseRepository.deleteWarehouse(warehouseId);
    }
}
