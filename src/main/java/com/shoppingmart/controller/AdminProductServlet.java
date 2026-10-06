package com.shoppingmart.controller;

import com.shoppingmart.model.Product;
import com.shoppingmart.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-products")
public class AdminProductServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");

        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Product> products = productService.getAllProducts();

        request.setAttribute("products", products);

        request.getRequestDispatcher(
                "admin-products.jsp").forward(request, response);
    }
}