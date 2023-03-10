package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.List;

public interface CartDao {

    void add(Product product);

    void remove(Product product);
    HashMap<Product, Integer> getAll();

}
