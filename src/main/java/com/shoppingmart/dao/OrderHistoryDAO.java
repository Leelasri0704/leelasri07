package com.shoppingmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderHistoryDAO {

    public void getOrderHistory(String email) {

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

            while (rs.next()) {

                System.out.println(
                        "Order ID: " + rs.getInt("order_id"));

                System.out.println(
                        "Total: Rs. " + rs.getDouble("total_amount"));

                System.out.println(
                        "Date: " + rs.getTimestamp("order_date"));

                System.out.println(
                        "Status: " + rs.getString("status"));

                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}