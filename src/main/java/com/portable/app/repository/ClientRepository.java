package com.portable.app.repository;

import com.portable.app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Procedure(procedureName = "Ventas.usp_ListarClientes")
    List<Client> listClients();

    @Query(value = "EXEC Ventas.usp_CrearCliente :nombre, :ape_pat, :ape_mat, :ruc_dni, :direccion, :telefono, :email", nativeQuery = true)
    Client createClient(
        @Param("nombre") String nombre,
        @Param("ape_pat") String apePat,
        @Param("ape_mat") String apeMat,
        @Param("ruc_dni") String rucDni,
        @Param("direccion") String direccion,
        @Param("telefono") String telefono,
        @Param("email") String email
    );

    @Modifying
    @Query(value = "EXEC Ventas.usp_ActualizarCliente :id_cliente, :nombre, :ape_pat, :ape_mat, :ruc_dni, :direccion, :telefono, :email", nativeQuery = true)
    void updateClient(
        @Param("id_cliente") Integer idCliente,
        @Param("nombre") String nombre,
        @Param("ape_pat") String apePat,
        @Param("ape_mat") String apeMat,
        @Param("ruc_dni") String rucDni,
        @Param("direccion") String direccion,
        @Param("telefono") String telefono,
        @Param("email") String email
    );

    @Modifying
    @Procedure(procedureName = "Ventas.usp_BorrarCliente")
    void deleteClient(@Param("id_cliente") Integer clientId);
}