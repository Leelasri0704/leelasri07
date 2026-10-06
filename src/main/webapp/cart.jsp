<%@ page import="java.util.List" %>
<%@ page import="com.shoppingmart.model.CartItem" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Mart - Cart</title>

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
            width: 80%;
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

        .total {
            text-align: center;
            font-size: 20px;
            font-weight: bold;
            margin: 20px;
        }

        .buttons {
            text-align: center;
            margin-top: 25px;
        }

        .btn {
            display: inline-block;
            padding: 12px 25px;
            margin: 5px;
            background: #222;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .empty {
            text-align: center;
            margin-top: 50px;
            font-size: 20px;
        }
    </style>
</head>

<body>

<h2>🛒 Shopping Cart</h2>

<%
    List<CartItem> cart =
        (List<CartItem>) session.getAttribute("cart");

    double grandTotal = 0;

    if (cart != null && !cart.isEmpty()) {
%>

<table>

    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Total</th>
    </tr>

<%
        for (CartItem item : cart) {

            grandTotal += item.getTotal();
%>

    <tr>
        <td>
            <%= item.getProduct().getProductName() %>
        </td>

        <td>
            Rs. <%= item.getProduct().getPrice() %>
        </td>

        <td>
            <%= item.getQuantity() %>
        </td>

        <td>
            Rs. <%= item.getTotal() %>
        </td>
    </tr>

<%
        }
%>

</table>

<div class="total">
    Grand Total: Rs. <%= grandTotal %>
</div>

<div class="buttons">

    <a href="products" class="btn">
        Continue Shopping
    </a>

    <a href="checkout.jsp" class="btn">
        Checkout
    </a>

</div>

<%
    } else {
%>

<div class="empty">
    Your cart is empty.
</div>

<div class="buttons">
    <a href="products" class="btn">
        Start Shopping
    </a>
</div>

<%
    }
%>

</body>
</html>