package com.portable.app.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.PurchaseDetail;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Integer> {
    
    @Procedure(procedureName = "Compras.usp_ListarDetalleCompra")
    List<PurchaseDetail> listPurchaseDetails();
    
    @Query(value = "EXEC Compras.usp_InsertarDetalleCompra :id_compra, :id_producto, :cantidad, :pre_uni", nativeQuery = true)
    Integer createPurchaseDetail(
        @Param("id_compra") Integer purchaseId,
        @Param("id_producto") Integer productId,
        @Param("cantidad") Integer quantity,
        @Param("precio_unitario") BigDecimal unitPrice
    );

    @Modifying
    @Query(value = "EXEC Compras.usp_ActualizarDetalleCompra :id_detalle_compra, :id_compra, :id_producto, :cantidad, :pre_uni", nativeQuery = true)
    void updatePurchaseDetail(
        @Param("id_det_com") Integer purchaseDetailId,
        @Param("id_compra") Integer purchaseId,
        @Param("id_producto") Integer productId,
        @Param("cantidad") Integer quantity,
        @Param("precio_unitario") BigDecimal unitPrice
    );

    @Procedure(procedureName = "Compras.usp_BorrarDetalleCompra")
    void deletePurchaseDetail(Integer purchaseDetailId);
}
