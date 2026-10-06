package com.shoppingmart.controller;

import com.shoppingmart.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-orders")
public class AdminOrderServlet extends HttpServlet {

    private final OrderService orderService =
            new OrderService();

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

        List<String[]> orders =
                orderService.getAllOrders();

        request.setAttribute("orders", orders);

        request.getRequestDispatcher(
                "/WEB-INF/admin-orders.jsp"
        ).forward(request, response);
    }
}