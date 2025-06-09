package com.portable.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Colocar procedimientos almacenados a implementar
}
