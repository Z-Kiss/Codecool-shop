package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DaoFactory;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.service.CartService;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

        private final DaoFactory daoFactory = DaoFactory.getInstance();
        private final CartService cartService = new CartService(daoFactory.getCartDao(), ProductDaoMem.getInstance());
        private Gson gson = new Gson();

    public CartController() throws IOException, SQLException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        context.setVariable("products", cartService.getAllProductsInCart());

        engine.process("product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = Integer.parseInt(req.getParameter("product"));
            String change = req.getParameter("change");
            if (change.equals("plus")){
                cartService.addProductToCart(id);
            } else if (change.equals("minus")) {
                cartService.removeProductFromCart(id);
            }


    }
}
