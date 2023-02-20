package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.model.Product;

import java.util.HashMap;

public class CartService {

    private CartDao cartDao;
    private ProductDao productDao;

    private Integer idOfActiveUser;

    public CartService(CartDao cartDao, ProductDao productDao){
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    public void addProductToCart(int id){
        cartDao.add(productDao.find(id));
    }

    public void removeProductFromCart(int id){cartDao.remove(productDao.find(id));}
    public HashMap<Product, Integer> getAllProductsInCart(){
        return cartDao.getAll();
    }

    public void setIdOfActiveUser(int id){
        this.idOfActiveUser = id;
    }
}
