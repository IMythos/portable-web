package com.portable.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Procedure(procedureName = "Administracion.usp_ListarUsuarios")
    List<User> listUsers();

    @Query(value = "EXEC Administracion.usp_InsertarUsuario :nom_usu, :clave, :estado, :id_rol, :id_empleado", nativeQuery = true)
    Integer insertUser(
        @Param("nom_usu") String username,
        @Param("clave") String password,
        @Param("estado") boolean status,
        @Param("id_rol") Integer roleId,
        @Param("id_empleado") Integer employeeId
    );

    @Modifying
    @Query(value = "EXEC Administracion.usp_ActualizarUsuario :id_usuario, :nom_usu, :clave, :estado, :id_rol, :id_empleado", nativeQuery = true)
    void updateUser(
        @Param("id_usuario") Integer userId,
        @Param("nom_usu") String username,
        @Param("clave") String password,
        @Param("estado") boolean status,
        @Param("id_rol") Integer roleId,
        @Param("id_empleado") Integer employeeId
    );

    @Procedure(procedureName = "Administracion.usp_BorrarUsuario")
    void deleteUser(Integer userId);
}
