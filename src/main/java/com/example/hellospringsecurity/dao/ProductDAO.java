package com.example.hellospringsecurity.dao;

import com.example.hellospringsecurity.Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO implements IProductDAO{
    private EntityManager entityManager;
    @Autowired
    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void saveProduct(Product product) {
        this.entityManager.persist(product);
    }

    @Override
    @Transactional
    public Product getProductById(Integer id) {
        return this.entityManager.find(Product.class, id);
    }

    @Override
    @Transactional
    public List<Product> getAllProduct() {
        return this.entityManager.createQuery("from Product ").getResultList();
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        this.entityManager.merge(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        Product product = getProductById(id);
        if (product != null){
            this.entityManager.remove(product);
        }
    }
}
