package com.shoppingmart.controller;

import com.shoppingmart.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-customers")
public class AdminCustomerServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerService();

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

        List<String[]> customers = customerService.getAllCustomers();

        request.setAttribute("customers", customers);

        request.getRequestDispatcher(
                "/WEB-INF/admin-customers.jsp").forward(request, response);
    }
}