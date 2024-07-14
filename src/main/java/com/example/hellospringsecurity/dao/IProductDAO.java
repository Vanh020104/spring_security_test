package com.example.hellospringsecurity.dao;

import com.example.hellospringsecurity.Entity.Product;

import java.util.List;

public interface IProductDAO {
    void saveProduct(Product product);
    Product getProductById(Integer id);
    List<Product> getAllProduct();
    void updateProduct(Product product);
    void deleteProduct(Integer id);
}
