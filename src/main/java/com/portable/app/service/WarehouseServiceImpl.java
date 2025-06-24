package com.portable.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.dto.WarehouseResponseDto;
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
                        (Integer) warehouse[0], // warehouseId
                        (String) warehouse[1], // warehouseCode
                        (String) warehouse[2]  // warehouseName
                ))
                .collect(Collectors.toList());
    }
}
