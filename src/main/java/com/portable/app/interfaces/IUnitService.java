package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.dto.UnitBrandDto;

public interface IUnitService {
    List<UnitBrandDto> listUnitsPerBrand();
}
