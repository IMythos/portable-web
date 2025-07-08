package com.portable.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portable.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Procedure(procedureName = "Inventario.usp_ListarProductos")
    List<Product> listProductsForSP();

    @Query(value = "EXEC Inventario.usp_InsertarProducto :cod_prod, :cod_anexo, :descripcion, :pre_ven, :pre_com, :pre_may, :categoria, :estado, :marca", nativeQuery = true)
    Integer insertProduct(
        @Param("cod_prod") String codProd,
        @Param("cod_anexo") String codAnexo,
        @Param("descripcion") String descripcion,
        @Param("pre_ven") Double preVen,
        @Param("pre_com") Double preCom,
        @Param("pre_may") Double preMay,
        @Param("categoria") String categoria,
        @Param("estado") Boolean estado,
        @Param("marca") String marca
    );

    @Modifying
    @Query(value = "EXEC Inventario.usp_ActualizarProducto :id_producto, :cod_prod, :cod_anexo, :descripcion, :pre_ven, :pre_com, :pre_may, :categoria, :estado, :marca", nativeQuery = true)
    void updateProduct(
        @Param("id_producto") Integer idProducto,
        @Param("cod_prod") String codProd,
        @Param("cod_anexo") String codAnexo,
        @Param("descripcion") String descripcion,
        @Param("pre_ven") Double preVen,
        @Param("pre_com") Double preCom,
        @Param("pre_may") Double preMay,
        @Param("categoria") String categoria,
        @Param("estado") Boolean estado,
        @Param("marca") String marca
    );

    @Procedure(procedureName = "Inventario.usp_BorrarProducto")
    void deleteProduct(Integer id_producto);

    @Procedure(procedureName = "Inventario.usp_Top5MarcasPorCantidad")
    List<Object[]> top5BrandsByProductCount();

    @Procedure(procedureName = "Inventario.usp_Top5ProductosPorPrecioVenta")
    List<Object[]> top5ProductsBySalePrice();

    Optional<Product> findById(Integer productId);
}
