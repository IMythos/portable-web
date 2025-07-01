package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer>{

    @Procedure(procedureName="Inventario.sp_Top7UnidadesPorMarca")
    List<Object[]> top7UnitsByBrand();

    @Procedure(procedureName = "Inventario.usp_ListarUnidades")
    List<Unit> listUnits();

    @Query(value = "EXEC Inventario.usp_InsertarUnidad :id_producto, :id_almacen, :serie, :estado", nativeQuery = true)
    Integer insertUnit(
        @Param("id_producto") Integer productId,
        @Param("id_almacen") Integer warehouseId,
        @Param("serie") String series,
        @Param("estado") String status
    );

    @Modifying
    @Query(value = "EXEC Inventario.usp_ActualizarUnidad :id_unidad, :id_producto, :id_almacen, :serie, :estado", nativeQuery = true)
    void updateUnit(
        @Param("id_unidad") Integer unitId,
        @Param("id_producto") Integer productId,
        @Param("id_almacen") Integer warehouseId,
        @Param("serie") String series,
        @Param("estado") String status
    );

    @Procedure(procedureName = "Inventario.usp_BorrarUnidad")
    void deleteUnit(Integer idUnit);
}
