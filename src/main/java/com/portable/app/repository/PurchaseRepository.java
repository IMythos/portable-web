package com.portable.app.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Procedure(procedureName = "Compras.usp_ListarCompras")
    List<Purchase> listPurchases();

    @Query(value = "EXEC Compras.usp_InsertarCompra :id_usuario, :id_proveedor, :fecha, :total", nativeQuery = true)
    Integer insertPurchase(
        @Param("id_usuario") Integer userId,
        @Param("id_proveedor") Integer providerId,
        @Param("fecha") LocalDate purchaseDate,
        @Param("total") BigDecimal totalAmount
    );

    @Modifying
    @Query(value = "EXEC Compras.usp_ActualizarCompra :id_compra, :id_usuario, :id_proveedor, :fecha, :total", nativeQuery = true)
    void updatePurchase(
        @Param("id_compra") Integer purchaseId,
        @Param("id_usuario") Integer userId,
        @Param("id_proveedor") Integer providerId,
        @Param("fecha") LocalDate purchaseDate,
        @Param("total") BigDecimal totalAmount
    );

    @Procedure(procedureName = "Compras.usp_BorrarCompra")
    void deletePurchase(Integer purchaseId);
}
