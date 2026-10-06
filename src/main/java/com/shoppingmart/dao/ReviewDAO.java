package com.shoppingmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReviewDAO {

    public boolean addReview(String email, int productId,
            int rating, String comment) {

        String userSql = "SELECT user_id FROM users WHERE email = ?";

        String reviewSql = "INSERT INTO reviews " +
                "(user_id, product_id, rating, comment) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement userPs = con.prepareStatement(userSql)) {

            userPs.setString(1, email);

            var rs = userPs.executeQuery();

            if (!rs.next()) {
                return false;
            }

            int userId = rs.getInt("user_id");

            try (PreparedStatement reviewPs = con.prepareStatement(reviewSql)) {

                reviewPs.setInt(1, userId);
                reviewPs.setInt(2, productId);
                reviewPs.setInt(3, rating);
                reviewPs.setString(4, comment);

                return reviewPs.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}