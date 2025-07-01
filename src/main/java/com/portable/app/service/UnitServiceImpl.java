package com.portable.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.dto.UnitBrandDto;
import com.portable.app.dto.UnitDto;
import com.portable.app.entity.Unit;
import com.portable.app.interfaces.IUnitService;
import com.portable.app.repository.UnitRepository;

@Service
public class UnitServiceImpl implements IUnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UnitBrandDto> listUnitsPerBrand() {
        return unitRepository.top7UnitsByBrand()
            .stream()
            .map(unit -> new UnitBrandDto(
                (String) unit[0],
                ((Number) unit[1]).intValue()
            ))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UnitDto> listUnits() {
        List<Unit> units = unitRepository.listUnits();
        return units.stream()
            .map(unit -> new UnitDto(
                unit.getUnitId(),
                unit.getProduct().getProductId(),
                unit.getWarehouse().getWarehouseId(),
                unit.getSeries(),
                unit.getStatus()
            ))
            .toList();
    }

    @Override
    @Transactional
    public UnitDto createUnit(UnitDto unitDto) {
        Integer newId = unitRepository.insertUnit(
            unitDto.getProductId(),
            unitDto.getWarehouseId(),
            unitDto.getSeries(),
            unitDto.getStatus()
        );

        unitDto.setUnitId(newId);
        return unitDto;
    }

    @Override
    @Transactional
    public void updateUnit(UnitDto unitDto) {
        unitRepository.updateUnit(
            unitDto.getUnitId(),
            unitDto.getProductId(),
            unitDto.getWarehouseId(),
            unitDto.getSeries(),
            unitDto.getStatus()
        );
    }

    @Override
    @Transactional
    public void deleteUnit(Integer unitId) {
        unitRepository.deleteUnit(unitId);
    }

}
