package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    
    @Procedure(procedureName = "Compras.usp_ListarProveedores")
    List<Provider> listProviders();

    @Query(value = "EXEC Compras.usp_InsertarProveedor :cod_prov, :nom_prov, :ruc_prov", nativeQuery = true)
    Integer insertProvider(
        @Param("cod_prov") String codProv,
        @Param("nom_prov") String nomProv,
        @Param("ruc_prov") String rucProv
    );

    @Modifying
    @Query(value = "EXEC Compras.usp_ActualizarProveedor :id_proveedor, :cod_prov, :nom_prov, :ruc_prov", nativeQuery = true)
    void updateProvider(
        @Param("id_proveedor") Integer idProveedor,
        @Param("cod_prov") String codProv,
        @Param("nom_prov") String nomProv,
        @Param("ruc_prov") String rucProv
    );

    @Procedure(procedureName = "Compras.usp_BorrarProveedor")
    void deleteProvider(Integer idProveedor);
}
