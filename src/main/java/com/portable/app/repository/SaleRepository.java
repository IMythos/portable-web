package com.portable.app.repository;

import com.portable.app.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Procedure(procedureName = "Ventas.usp_ListarVentas")
    List<Sale> listSales();

    @Query(value = "EXEC Ventas.usp_InsertarVenta :id_cliente, :id_usuario, :id_comprobante, :tipo_pago, :tipo_ven, :total", nativeQuery = true)
    Integer insertSale(
        @Param("id_cliente") Integer clientId,
        @Param("id_usuario") Integer userId,
        @Param("id_comprobante") Integer receiptId,
        @Param("tipo_pago") String paymentType,
        @Param("tipo_ven") Boolean saleType,
        @Param("total") BigDecimal total
    );

    @Modifying
    @Query(value = "EXEC Ventas.usp_ActualizarVenta :id_venta, :id_cliente, :id_usuario, :id_comprobante, :tipo_pago, :tipo_ven, :total", nativeQuery = true)
    void updateSale(
        @Param("id_venta") Integer saleId,
        @Param("id_cliente") Integer clientId,
        @Param("id_usuario") Integer userId,
        @Param("id_comprobante") Integer receiptId,
        @Param("tipo_pago") String paymentType,
        @Param("tipo_ven") Boolean saleType,
        @Param("total") BigDecimal total
    );

    @Modifying
    @Procedure(procedureName = "Ventas.usp_BorrarVenta")
    void deleteSale(@Param("id_venta") Integer saleId);
}