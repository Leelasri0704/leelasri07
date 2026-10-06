package com.shoppingmart.service;

import com.shoppingmart.dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<String[]> getAllOrders() {

        List<String[]> orders = new ArrayList<>();

        String sql =
                "SELECT o.order_id, u.name, u.email, " +
                "o.total_amount, o.order_date, o.status " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.user_id " +
                "ORDER BY o.order_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String[] order = {
                    String.valueOf(rs.getInt("order_id")),
                    rs.getString("name"),
                    rs.getString("email"),
                    String.valueOf(rs.getDouble("total_amount")),
                    String.valueOf(rs.getTimestamp("order_date")),
                    rs.getString("status")
                };

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}
