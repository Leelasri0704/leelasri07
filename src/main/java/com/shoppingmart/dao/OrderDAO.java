package com.shoppingmart.dao;

import com.shoppingmart.model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OrderDAO {

    public boolean placeOrder(String email, List<CartItem> cart) {

        String userSql = "SELECT user_id FROM users WHERE email = ?";

        String orderSql = "INSERT INTO orders (user_id, total_amount) VALUES (?, ?)";

        String itemSql = "INSERT INTO order_items " +
                "(order_id, product_id, quantity, price) " +
                "VALUES (?, ?, ?, ?)";

        String stockSql = "UPDATE products SET stock = stock - ? " +
                "WHERE product_id = ? AND stock >= ?";

        Connection con = null;

        try {
            con = DBConnection.getConnection();

            con.setAutoCommit(false);

            int userId;

            // Find user
            try (PreparedStatement ps = con.prepareStatement(userSql)) {

                ps.setString(1, email);

                try (ResultSet rs = ps.executeQuery()) {

                    if (!rs.next()) {
                        con.rollback();
                        return false;
                    }

                    userId = rs.getInt("user_id");
                }
            }

            // Calculate total
            double totalAmount = 0;

            for (CartItem item : cart) {
                totalAmount += item.getTotal();
            }

            int orderId;

            // Create order
            try (PreparedStatement ps = con.prepareStatement(
                    orderSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, userId);
                ps.setDouble(2, totalAmount);

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {

                    if (!rs.next()) {
                        con.rollback();
                        return false;
                    }

                    orderId = rs.getInt(1);
                }
            }

            // Add order items and reduce stock
            for (CartItem item : cart) {

                int productId = item.getProduct().getProductId();

                int quantity = item.getQuantity();

                // Reduce stock
                try (PreparedStatement ps = con.prepareStatement(stockSql)) {

                    ps.setInt(1, quantity);
                    ps.setInt(2, productId);
                    ps.setInt(3, quantity);

                    int updated = ps.executeUpdate();

                    if (updated == 0) {
                        con.rollback();
                        return false;
                    }
                }

                // Add order item
                try (PreparedStatement ps = con.prepareStatement(itemSql)) {

                    ps.setInt(1, orderId);
                    ps.setInt(2, productId);
                    ps.setInt(3, quantity);
                    ps.setDouble(4, item.getProduct().getPrice());

                    ps.executeUpdate();
                }
            }

            con.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ignored) {
            }

            return false;

        } finally {

            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception ignored) {
            }
        }
    }
}