package com.learning.spring.service;

import com.learning.spring.model.Product;

import java.util.Collection;

public interface ProductService {

    public void createProduct(Product product);
    public void updateProduct(String id, Product product);
    public void deleteProduct(String id);
    public Collection<Product> getProducts();
}
