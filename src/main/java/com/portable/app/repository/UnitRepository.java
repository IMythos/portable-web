package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer>{

    @Procedure(procedureName="Inventario.sp_Top7UnidadesPorMarca")
    List<Object[]> top7UnitsByBrand();
}
