package com.codecool.shop.controller;

import com.codecool.shop.dao.DataSource;
import com.codecool.shop.dao.implementation.jdbc.UserDaoJdbc;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;
import com.google.gson.Gson;
import utils.PasswordHasher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private final UserService userService = new UserService(new UserDaoJdbc(DataSource.getInstance()), PasswordHasher.getInstance());

    public RegisterController() throws SQLException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        User user = gson.fromJson(reader, User.class);
        userService.register(user);

    }
}
