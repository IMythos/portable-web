package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Procedure(procedureName = "Inventario.usp_ListarSucursales")
    List<Branch> listBranches();

    @Query(value = "EXEC Inventario.usp_InsertarSucursal :nom_suc, :distrito, :direccion", nativeQuery = true)
    Integer insertBranch(
        @Param("nom_suc") String nomSucursal,
        @Param("distrito") String distrito,
        @Param("direccion") String direccion
    );

    @Modifying
    @Query(value = "EXEC Inventario.usp_ActualizarSucursal :id_sucursal, :nom_suc, :distrito, :direccion", nativeQuery = true)
    void updateBranch(
        @Param("id_sucursal") Integer idSucursal,
        @Param("nom_suc") String nomSucursal,
        @Param("distrito") String distrito,
        @Param("direccion") String direccion
    );

    @Procedure(procedureName = "Inventario.usp_BorrarSucursal")
    void deleteBranch(Integer idSucursal);
}
