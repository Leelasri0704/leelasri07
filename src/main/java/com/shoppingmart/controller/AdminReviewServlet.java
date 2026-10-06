package com.shoppingmart.controller;

import com.shoppingmart.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;

@WebServlet("/admin-reviews")
public class AdminReviewServlet extends HttpServlet {

    private final ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                !"ADMIN".equalsIgnoreCase(
                        (String) session.getAttribute("role"))) {

            response.sendRedirect("login.jsp");
            return;
        }

        ResultSet reviews = reviewService.getAllReviews();

        request.setAttribute("reviews", reviews);

        request.getRequestDispatcher(
                "/WEB-INF/admin-reviews.jsp").forward(request, response);
    }
}
