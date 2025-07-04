package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    @Procedure(procedureName = "Inventario.sp_ObtenerAlmacenes")
    List<Object[]> getWarehouses();

    @Procedure(procedureName = "Inventario.usp_ListarAlmacenes")
    List<Warehouse> listWarehouses();

    @Query(value = "EXEC Inventario.usp_InsertarAlmacen :id_sucursal, :cod_alm, :nom_alm", nativeQuery = true)
    Integer insertWarehouse(
        @Param("id_sucursal") Integer branchId,
        @Param("cod_alm") String warehouseCode,
        @Param("nom_alm") String warehouseName
    );

    @Modifying
    @Query(value = "EXEC Inventario.usp_ActualizarAlmacen :id_almacen, :id_sucursal, :cod_alm, :nom_alm", nativeQuery = true)
    void updateWarehouse(
        @Param("id_almacen") Integer warehouseId,
        @Param("id_sucursal") Integer branchId,
        @Param("cod_alm") String warehouseCode,
        @Param("nom_alm") String warehouseName
    );

    @Procedure(procedureName = "Inventario.usp_BorrarAlmacen")
    void deleteWarehouse(Integer warehouseId);
}
