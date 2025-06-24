package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.WarehouseResponseDto;

public interface IWarehouseService {
    List<WarehouseResponseDto> listWarehouses();
}
