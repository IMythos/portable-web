package com.portable.app.repository;

import com.portable.app.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    @Procedure(procedureName = "Ventas.usp_ListarComprobantes")
    List<Receipt> listReceipts();

    @Query(value = "EXEC Ventas.usp_InsertarComprobante :documento, :serie, :numero, :fecha_emision, :fecha_venc, :moneda", nativeQuery = true)
    Integer insertReceipt(
        @Param("documento") String document,
        @Param("serie") String series,
        @Param("numero") String number,
        @Param("fecha_emision") LocalDateTime issueDate,
        @Param("fecha_venc") LocalDateTime dueDate,
        @Param("moneda") String currency
    );

    @Modifying
    @Query(value = "EXEC Ventas.usp_ActualizarComprobante :id_comprobante, :documento, :serie, :numero, :fecha_emision, :fecha_venc, :moneda", nativeQuery = true)
    void updateReceipt(
        @Param("id_comprobante") Integer receiptId,
        @Param("documento") String document,
        @Param("serie") String series,
        @Param("numero") String number,
        @Param("fecha_emision") LocalDateTime issueDate,
        @Param("fecha_venc") LocalDateTime dueDate,
        @Param("moneda") String currency
    );

    @Modifying
    @Procedure(procedureName = "Ventas.usp_BorrarComprobante")
    void deleteReceipt(@Param("id_comprobante") Integer receiptId);
}