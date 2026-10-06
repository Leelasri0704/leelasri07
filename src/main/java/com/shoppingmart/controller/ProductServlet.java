package com.shoppingmart.controller;

import com.shoppingmart.dao.ProductDAO;
import com.shoppingmart.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = productDAO.getAllProducts();

        request.setAttribute("products", products);

        request.getRequestDispatcher("products.jsp")
                .forward(request, response);
    }
}
