package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.entity.Product;

public interface IProductService {
    List<Product> listProducts();
    Product createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer productId);
}
