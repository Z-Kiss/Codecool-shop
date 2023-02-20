package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;

public class CartDaoMem implements CartDao {
    private static CartDaoMem instance = null;
    private HashMap<Product, Integer> productsInCart = new HashMap<>();

    @Override
    public void add(Product product) {
        productsInCart.merge(product, 1, Integer::sum);
    }

    @Override
    public void remove(Product product) {
        int amount = productsInCart.get(product);
        if (amount == 1){
            productsInCart.remove(product);
        } else {
        productsInCart.put(product, amount - 1);}
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return this.productsInCart;
    }

    public static CartDao getInstance(){
        if (instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }
}
