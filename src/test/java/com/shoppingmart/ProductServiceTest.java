package com.shoppingmart;

import com.shoppingmart.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private final ProductService productService =
            new ProductService();

    @Test
    void shouldRejectInvalidProduct() {

        boolean result =
                productService.addProduct(
                        "",
                        100,
                        10
                );

        assertFalse(result);
    }

    @Test
    void shouldRejectNegativePrice() {

        boolean result =
                productService.addProduct(
                        "Test Product",
                        -100,
                        10
                );

        assertFalse(result);
    }

    @Test
    void shouldRejectNegativeStock() {

        boolean result =
                productService.addProduct(
                        "Test Product",
                        100,
                        -5
                );

        assertFalse(result);
    }

    @Test
    void shouldRejectInvalidProductId() {

        boolean result =
                productService.updateStock(
                        0,
                        10
                );

        assertFalse(result);
    }
}