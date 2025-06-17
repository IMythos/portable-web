package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.entity.Product;
import com.portable.app.interfaces.IProductService;
import com.portable.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        return productRepository.listProductsForSP()
            .stream()
            .map(product -> new Product(
                product.getProductId(),
                product.getProductCode(),
                product.getProductAnnex(),
                product.getDescription(),
                product.getSalePrice(),
                product.getPurchasePrice(),
                product.getWholeSale(),
                product.getCategory(),
                product.getStatus(),
                product.getBrand()
            ))
            .toList();
    }
}
