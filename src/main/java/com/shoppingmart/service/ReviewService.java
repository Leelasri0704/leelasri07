package com.shoppingmart.service;

import com.shoppingmart.dao.ReviewDAO;

import java.sql.ResultSet;

public class ReviewService {

    private final ReviewDAO reviewDAO = new ReviewDAO();

    public ResultSet getAllReviews() {
        return reviewDAO.getAllReviews();
    }
}