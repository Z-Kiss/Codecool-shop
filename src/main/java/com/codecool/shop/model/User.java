package com.codecool.shop.model;

import utils.PasswordHasher;

import java.lang.reflect.Field;

public class User {
    private String name;
    private String email;
    private String password;
    private int id;

    public User(int id,String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }


    @Override
    public String toString() {
        return String.format("name %s, email %s, password %s",
                this.name,
                this.email,
                this.password
        );
    }
}
