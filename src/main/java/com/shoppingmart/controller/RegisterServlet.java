package com.shoppingmart.controller;

import com.shoppingmart.dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || email == null || password == null ||
                name.isBlank() || email.isBlank() || password.isBlank()) {

            response.getWriter().println(
                    "Please fill all fields.");
            return;
        }

        // Hash password using BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        boolean success = userDAO.registerUser(
                name,
                email,
                hashedPassword);

        if (success) {

            response.getWriter().println(
                    "Registration successful!");

        } else {

            response.getWriter().println(
                    "Registration failed!");
        }
    }
}