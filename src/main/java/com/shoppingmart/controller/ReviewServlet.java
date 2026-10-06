package com.shoppingmart.controller;

import com.shoppingmart.dao.ReviewDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private final ReviewDAO reviewDAO = new ReviewDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("userEmail");

        if (email == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int productId = Integer.parseInt(
                request.getParameter("productId"));

        int rating = Integer.parseInt(
                request.getParameter("rating"));

        String comment = request.getParameter("comment");

        if (rating < 1 || rating > 5) {
            response.getWriter().println(
                    "Rating must be between 1 and 5.");
            return;
        }

        boolean success = reviewDAO.addReview(
                email,
                productId,
                rating,
                comment);

        if (success) {
            response.sendRedirect("review-success.jsp");
        } else {
            response.getWriter().println(
                    "Review submission failed.");
        }
    }
}
