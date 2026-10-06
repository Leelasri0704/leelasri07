package com.shoppingmart.service;

import com.shoppingmart.dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<String[]> getAllCustomers() {

        List<String[]> customers = new ArrayList<>();

        String sql = "SELECT user_id, name, email, role " +
                "FROM users ORDER BY user_id";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String[] customer = {
                        String.valueOf(rs.getInt("user_id")),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role")
                };

                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }
}