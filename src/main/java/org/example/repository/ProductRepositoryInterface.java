package org.example.repository;

import org.example.model.Product;

public interface ProductRepositoryInterface {
    Product findById(Long id) throws Exception;
    void readAllProducts(String path) throws Exception;
}
