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

    @Override
    @Transactional
    public Product createProduct(Product product) {
        Integer newId = productRepository.insertProduct(
            product.getProductCode(),
            product.getProductAnnex(),
            product.getDescription(),
            product.getSalePrice().doubleValue(),
            product.getPurchasePrice().doubleValue(),
            product.getWholeSale().doubleValue(),
            product.getCategory(),
            product.getStatus(),
            product.getBrand()
        );
        product.setProductId(newId);
        return product;
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productRepository.updateProduct(
            product.getProductId(),
            product.getProductCode(),
            product.getProductAnnex(),
            product.getDescription(),
            product.getSalePrice().doubleValue(),
            product.getPurchasePrice().doubleValue(),
            product.getWholeSale().doubleValue(),
            product.getCategory(),
            product.getStatus(),
            product.getBrand()
        );
    }

    @Override
    @Transactional
    public void deleteProduct(Integer productId) {
        productRepository.deleteProduct(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> top5BrandsByProductCount() {
        return productRepository.top5BrandsByProductCount();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> top5ProductsBySalePrice() {
        return productRepository.top5ProductsBySalePrice();
    }

    @Override
    public Product findProductById(Integer productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID: " + productId));
        
        return new Product(
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
        );
    }
}
