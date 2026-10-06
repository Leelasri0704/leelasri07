<%@ page import="java.util.List" %>
<%@ page import="com.shoppingmart.model.CartItem" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Mart - Checkout</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 40px;
        }

        .box {
            width: 500px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
        }

        .item {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .total {
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            margin: 20px;
        }

        .btn {
            display: block;
            width: 90%;
            padding: 12px;
            margin: 10px auto;
            background: #222;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="box">

    <h2>🛒 Checkout</h2>

<%
    List<CartItem> cart =
        (List<CartItem>) session.getAttribute("cart");

    double grandTotal = 0;

    if (cart != null && !cart.isEmpty()) {

        for (CartItem item : cart) {

            grandTotal += item.getTotal();
%>

    <div class="item">
        <b><%= item.getProduct().getProductName() %></b>
        <br>
        Quantity: <%= item.getQuantity() %>
        <br>
        Total: Rs. <%= item.getTotal() %>
    </div>

<%
        }
%>

    <div class="total">
        Grand Total: Rs. <%= grandTotal %>
    </div>

    <form action="place-order" method="post">

        <button type="submit" class="btn">
            Place Order
        </button>

    </form>

    <a href="products" class="btn">
        Continue Shopping
    </a>

<%
    } else {
%>

    <p style="text-align:center;">
        Your cart is empty.
    </p>

    <a href="products" class="btn">
        Start Shopping
    </a>

<%
    }
%>

</div>

</body>
</html>