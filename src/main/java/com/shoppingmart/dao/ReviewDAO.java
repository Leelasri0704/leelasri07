package com.shoppingmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    // Admin - View all reviews
    public ResultSet getAllReviews() {

        String sql = "SELECT r.review_id, u.name, " +
                "p.product_name, r.rating, r.comment " +
                "FROM reviews r " +
                "JOIN users u ON r.user_id = u.user_id " +
                "JOIN products p ON r.product_id = p.product_id " +
                "ORDER BY r.review_id DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}