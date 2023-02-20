package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    void register(User user);

    String getPasswordByEmail(String email);

    String getNameByEmail(String email);

    Integer getIdByEmail(String email);

    void createCart(int id);

}
