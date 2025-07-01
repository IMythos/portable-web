package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    @Procedure(procedureName = "Administracion.usp_ListarRol")
    List<Role> listRolesSP();

    @Query(value = "EXEC Administracion.usp_InsertarRol :nom_rol", nativeQuery = true)
    Integer insertRole(
        @Param("nom_rol") String nomRol
    );

    @Modifying
    @Query(value = "EXEC Administracion.usp_ActualizarRol :id_rol, :nom_rol", nativeQuery = true)
    void updateRole(
        @Param("id_rol") Integer idRol,
        @Param("nom_rol") String nomRol
    );

    @Procedure(procedureName = "Administracion.usp_BorrarRol")
    void deleteRole(Integer idRol);
}
