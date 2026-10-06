package com.shoppingmart.controller;

import com.shoppingmart.dao.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {

                String email = request.getParameter("email");
                String password = request.getParameter("password");

                if (email == null || password == null ||
                                email.isBlank() || password.isBlank()) {

                        response.getWriter().println(
                                        "Please enter email and password.");
                        return;
                }

                String sql = "SELECT user_id, name, role, password " +
                                "FROM users " +
                                "WHERE email = ?";

                try (Connection con = DBConnection.getConnection();
                                PreparedStatement ps = con.prepareStatement(sql)) {

                        ps.setString(1, email);

                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {

                                String storedPassword = rs.getString("password");

                                // Check BCrypt password
                                if (BCrypt.checkpw(password, storedPassword)) {

                                        HttpSession session = request.getSession();

                                        session.setAttribute(
                                                        "userId",
                                                        rs.getInt("user_id"));

                                        session.setAttribute(
                                                        "userName",
                                                        rs.getString("name"));

                                        session.setAttribute(
                                                        "userEmail",
                                                        email);

                                        session.setAttribute(
                                                        "role",
                                                        rs.getString("role"));

                                        String role = rs.getString("role");

                                        if ("ADMIN".equalsIgnoreCase(role)) {

                                                response.sendRedirect("admin.jsp");

                                        } else {

                                                response.sendRedirect("products");
                                        }

                                } else {

                                        response.getWriter().println(
                                                        "Invalid email or password.");
                                }

                        } else {

                                response.getWriter().println(
                                                "Invalid email or password.");
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        response.getWriter().println(
                                        "Login failed. Please try again.");
                }
        }
}