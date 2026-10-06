package com.shoppingmart.controller;

import com.shoppingmart.dao.OrderDAO;
import com.shoppingmart.model.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("userEmail");

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (email == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("products");
            return;
        }

        boolean success = orderDAO.placeOrder(email, cart);

        if (success) {

            session.removeAttribute("cart");

            response.sendRedirect("order-success.jsp");

        } else {

            response.getWriter().println(
                    "Order placement failed. Please try again.");
        }
    }
}
