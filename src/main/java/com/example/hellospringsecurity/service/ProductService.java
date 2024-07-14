package com.example.hellospringsecurity.service;


import com.example.hellospringsecurity.Entity.Product;
import com.example.hellospringsecurity.dao.IProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private IProductDAO productDAO;
    @Autowired
    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void saveProduct(Product product){
        productDAO.saveProduct(product);
    }

    public Product getProductById(Integer id){
        return productDAO.getProductById(id);
    }

    public List<Product> getAllProduct(){
        return productDAO.getAllProduct();
    }

    public  void updateProduct(Product product){
        productDAO.updateProduct(product);
    }
    public void deleteProduct(Integer id) {
        productDAO.deleteProduct(id);
    }
}
