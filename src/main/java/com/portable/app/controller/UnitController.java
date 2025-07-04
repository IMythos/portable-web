package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.dto.UnitBrandDto;
import com.portable.app.dto.UnitDto;
import com.portable.app.service.UnitServiceImpl;

@RestController
@RequestMapping("/v1/api/units")
public class UnitController {

    @Autowired
    private UnitServiceImpl unitServiceImpl;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public List<UnitDto> getUnits() {
        return unitServiceImpl.listUnits();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<UnitDto> createUnit(@RequestBody UnitDto unitDto) {
        try {
            UnitDto createdUnit = unitServiceImpl.createUnit(unitDto);
            return ResponseEntity.ok(createdUnit);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> updateUnit(@PathVariable Integer id, @RequestBody UnitDto unitDto) {
        try {
            unitDto.setUnitId(id);
            unitServiceImpl.updateUnit(unitDto);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteUnit(@PathVariable Integer id) {
        try {
            unitServiceImpl.deleteUnit(id);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/top/brands")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public List<UnitBrandDto> getTopBrands() {
        return unitServiceImpl.listUnitsPerBrand();
    }
}
