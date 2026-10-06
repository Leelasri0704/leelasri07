package com.shoppingmart.controller;

import com.shoppingmart.dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/order-history")
public class OrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("userEmail");

        if (email == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String sql = "SELECT o.order_id, o.total_amount, " +
                "o.order_date, o.status " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.user_id " +
                "WHERE u.email = ? " +
                "ORDER BY o.order_date DESC";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            request.setAttribute("orders", rs);

            request.getRequestDispatcher("order-history.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(
                    "Unable to load order history.");
        }
    }
}
