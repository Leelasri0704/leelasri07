package com.shoppingmart.controller;

import com.shoppingmart.dao.ProductDAO;
import com.shoppingmart.model.CartItem;
import com.shoppingmart.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(
                request.getParameter("productId"));

        int quantity = Integer.parseInt(
                request.getParameter("quantity"));

        if (quantity <= 0) {
            response.getWriter().println("Invalid quantity.");
            return;
        }

        List<Product> products = productDAO.getAllProducts();

        Product selectedProduct = null;

        for (Product product : products) {
            if (product.getProductId() == productId) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct == null) {
            response.getWriter().println("Product not found.");
            return;
        }

        if (quantity > selectedProduct.getStock()) {
            response.getWriter().println("Not enough stock.");
            return;
        }

        HttpSession session = request.getSession();

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;

        for (CartItem item : cart) {

            if (item.getProduct().getProductId() == productId) {

                int newQuantity = item.getQuantity() + quantity;

                if (newQuantity > selectedProduct.getStock()) {
                    response.getWriter().println("Not enough stock.");
                    return;
                }

                item.setQuantity(newQuantity);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(selectedProduct, quantity));
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("cart.jsp");
    }
}
