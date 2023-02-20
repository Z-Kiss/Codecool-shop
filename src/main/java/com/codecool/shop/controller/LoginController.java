package com.codecool.shop.controller;


import com.codecool.shop.dao.DataSource;
import com.codecool.shop.dao.implementation.jdbc.UserDaoJdbc;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;

import utils.PasswordHasher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {


    private final UserService userService = new UserService(new UserDaoJdbc(DataSource.getInstance()), PasswordHasher.getInstance());

    public LoginController() throws SQLException, IOException {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        User user = gson.fromJson(reader, User.class);

        if (!userService.verifyPassword(user)){
            resp.setStatus(400);
        }else{
            resp.setHeader("Cache-Control", "private, no-cache");
            resp.setHeader("Pragma", "no-cache");
            resp.setContentType("text/html");


            HttpSession session = req.getSession();
            session.setAttribute("user", userService.getNameByEmail(user));
            int id = userService.getIdByEmail(user);
            session.setAttribute("userId",id);
            userService.createTemporalyCart(id);




        }
    }

}
