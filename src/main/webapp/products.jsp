<%@ page import="java.util.List" %>
<%@ page import="com.shoppingmart.model.Product" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Mart - Products</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 30px;
        }

        h2 {
            text-align: center;
        }

        table {
            width: 95%;
            margin: 30px auto;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #222;
            color: white;
        }

        button {
            padding: 8px 12px;
            background: #222;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        input[type="number"] {
            width: 55px;
            padding: 7px;
        }

        textarea {
            width: 120px;
            height: 40px;
            resize: none;
        }

        select {
            padding: 7px;
        }

        .available {
            color: green;
            font-weight: bold;
        }

        .out {
            color: red;
            font-weight: bold;
        }

        .cart-link,
        .orders-link {
            display: inline-block;
            padding: 10px 20px;
            margin: 5px;
            background: #222;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .top-links {
            text-align: center;
        }

    </style>

</head>

<body>

<h2>🛒 Shopping Mart - Products</h2>

<div class="top-links">

    <a href="cart.jsp" class="cart-link">
        View Cart
    </a>

    <a href="order-history" class="orders-link">
        My Orders
    </a>

</div>

<table>

    <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Status</th>
        <th>Add to Cart</th>
        <th>Review</th>
    </tr>

<%
    List<Product> products =
        (List<Product>) request.getAttribute("products");

    if (products != null && !products.isEmpty()) {

        for (Product product : products) {
%>

    <tr>

        <td>
            <%= product.getProductId() %>
        </td>

        <td>
            <%= product.getProductName() %>
        </td>

        <td>
            Rs. <%= product.getPrice() %>
        </td>

        <td>
            <%= product.getStock() %>
        </td>

        <td>

            <% if (product.getStock() > 0) { %>

                <span class="available">
                    Available
                </span>

            <% } else { %>

                <span class="out">
                    Out of Stock
                </span>

            <% } %>

        </td>

        <td>

            <% if (product.getStock() > 0) { %>

                <form action="add-to-cart" method="post">

                    <input
                        type="hidden"
                        name="productId"
                        value="<%= product.getProductId() %>"
                    >

                    <input
                        type="number"
                        name="quantity"
                        value="1"
                        min="1"
                        max="<%= product.getStock() %>"
                    >

                    <button type="submit">
                        Add to Cart
                    </button>

                </form>

            <% } else { %>

                <span class="out">
                    Not Available
                </span>

            <% } %>

        </td>

        <td>

            <form action="review" method="post">

                <input
                    type="hidden"
                    name="productId"
                    value="<%= product.getProductId() %>"
                >

                <select name="rating" required>

                    <option value="">Rating</option>
                    <option value="5">5 ⭐</option>
                    <option value="4">4 ⭐</option>
                    <option value="3">3 ⭐</option>
                    <option value="2">2 ⭐</option>
                    <option value="1">1 ⭐</option>

                </select>

                <br><br>

                <textarea
                    name="comment"
                    placeholder="Your comment"
                    required
                ></textarea>

                <br><br>

                <button type="submit">
                    Submit Review
                </button>

            </form>

        </td>

    </tr>

<%
        }

    } else {
%>

    <tr>
        <td colspan="7">
            No products available.
        </td>
    </tr>

<%
    }
%>

</table>

</body>
</html>