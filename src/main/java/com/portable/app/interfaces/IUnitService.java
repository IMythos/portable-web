package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.UnitBrandDto;
import com.portable.app.dto.UnitDto;

public interface IUnitService {
    List<UnitBrandDto> listUnitsPerBrand();
    List<UnitDto> listUnits();
    UnitDto createUnit(UnitDto unitDto);
    void updateUnit(UnitDto unitDto);
    void deleteUnit(Integer unitId);
}
