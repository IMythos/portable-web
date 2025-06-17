package com.portable.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Procedure(procedureName = "Inventario.usp_ListarProductos")
    List<Product> listProductsForSP();
}
