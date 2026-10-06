package com.shoppingmart.controller;

import com.shoppingmart.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");

        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            response.sendRedirect("login.jsp");
            return;
        }

        String productName = request.getParameter("productName");

        String priceText = request.getParameter("price");

        String stockText = request.getParameter("stock");

        try {

            double price = Double.parseDouble(priceText);
            int stock = Integer.parseInt(stockText);

            boolean success = productService.addProduct(
                    productName,
                    price,
                    stock);

            if (success) {
                response.sendRedirect("admin-products");
            } else {
                response.getWriter().println(
                        "Invalid product details.");
            }

        } catch (NumberFormatException e) {

            response.getWriter().println(
                    "Please enter valid price and stock.");
        }
    }
}