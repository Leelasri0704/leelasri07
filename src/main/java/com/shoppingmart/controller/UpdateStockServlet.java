package com.shoppingmart.controller;

import com.shoppingmart.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/update-stock")
public class UpdateStockServlet extends HttpServlet {

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

        try {
            int productId = Integer.parseInt(
                    request.getParameter("productId"));

            int stock = Integer.parseInt(
                    request.getParameter("stock"));

            boolean success = productService.updateStock(
                    productId,
                    stock);

            if (success) {
                response.sendRedirect("admin-products");
            } else {
                response.getWriter().println(
                        "Invalid product ID or stock.");
            }

        } catch (NumberFormatException e) {

            response.getWriter().println(
                    "Please enter valid numbers.");
        }
    }
}