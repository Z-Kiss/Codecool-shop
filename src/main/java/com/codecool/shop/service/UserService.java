package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import utils.PasswordHasher;

public class UserService {

    private UserDao userDao;
    public PasswordHasher passwordHasher;

    public UserService(UserDao userDao, PasswordHasher passwordHasher){
        this.passwordHasher = passwordHasher;
        this.userDao = userDao;
    }

    public void register(User user){
        hashPassword(user);
        userDao.register(user);
    }

    private void hashPassword(User user){
        user.setPassword(passwordHasher.hashPassword(user.getPassword()));
    }

    public boolean verifyPassword(User user){
        return passwordHasher.verifyPassword(userDao.getPasswordByEmail(user.getEmail()),user.getPassword());
    }

    public String getNameByEmail(User user){
        return userDao.getNameByEmail(user.getEmail());
    }

    public int getIdByEmail(User user){ return userDao.getIdByEmail(user.getEmail()); }

    public void createTemporalyCart(int id){
        userDao.createCart(id);
    }
}
