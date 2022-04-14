package com.quokka.catalog.service;

import com.quokka.catalog.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product,String currentUser);
    Product getById(String idProduct);
    List<Product> findAllByColumnName(String columnName, Object Value);
}
