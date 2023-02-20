package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/api/filter/category"})
public class FilterByCategoryAPI extends HttpServlet {

    ProductService productService = new ProductService(ProductDaoMem.getInstance(), ProductCategoryDaoMem.getInstance(), SupplierDaoMem.getInstance());
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-Control", "private, no-cache");
        resp.setHeader("Pragma", "no-cache");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String categoryId = req.getParameter("id");
        PrintWriter out = resp.getWriter();
        List<Product> products =  productService.getProductsForCategory(Integer.parseInt(categoryId));
        out.println(gson.toJson(products));
    }
}
