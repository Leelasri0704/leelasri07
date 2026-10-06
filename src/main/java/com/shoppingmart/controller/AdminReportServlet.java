package com.shoppingmart.controller;

import com.shoppingmart.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin-reports")
public class AdminReportServlet extends HttpServlet {

    private final ReportService reportService =
            new ReportService();

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String role =
                (String) session.getAttribute("role");

        if (role == null ||
            !role.equalsIgnoreCase("ADMIN")) {

            response.sendRedirect("login.jsp");
            return;
        }

        int totalProducts =
                reportService.getTotalProducts();

        int totalCustomers =
                reportService.getTotalCustomers();

        int totalOrders =
                reportService.getTotalOrders();

        double totalSales =
                reportService.getTotalSales();

        request.setAttribute(
                "totalProducts",
                totalProducts
        );

        request.setAttribute(
                "totalCustomers",
                totalCustomers
        );

        request.setAttribute(
                "totalOrders",
                totalOrders
        );

        request.setAttribute(
                "totalSales",
                totalSales
        );

        request.getRequestDispatcher(
                "/WEB-INF/admin-reports.jsp"
        ).forward(request, response);
    }
}