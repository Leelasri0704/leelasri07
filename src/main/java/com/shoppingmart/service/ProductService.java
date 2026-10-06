package com.shoppingmart.service;

import com.shoppingmart.dao.ProductDAO;
import com.shoppingmart.model.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO = new ProductDAO();

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public boolean addProduct(String productName, double price, int stock) {
        if (productName == null || productName.isBlank()) {
            return false;
        }

        if (price <= 0 || stock < 0) {
            return false;
        }

        return productDAO.addProduct(productName, price, stock);
    }

    public boolean updateStock(int productId, int stock) {
        if (productId <= 0 || stock < 0) {
            return false;
        }

        return productDAO.updateStock(productId, stock);
    }

    public boolean deleteProduct(int productId) {
        if (productId <= 0) {
            return false;
        }

        return productDAO.deleteProduct(productId);
    }
}