package com.portable.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.dto.UnitBrandDto;
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

}
