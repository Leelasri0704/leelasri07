<%@ page import="java.sql.ResultSet" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Mart - Order History</title>

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
            width: 85%;
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

        .status {
            color: green;
            font-weight: bold;
        }

        .btn {
            display: block;
            width: 180px;
            margin: 25px auto;
            padding: 12px;
            text-align: center;
            background: #222;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>

<body>

<h2>📦 My Order History</h2>

<%
    ResultSet orders =
        (ResultSet) request.getAttribute("orders");

    boolean hasOrders = false;
%>

<table>

    <tr>
        <th>Order ID</th>
        <th>Total Amount</th>
        <th>Order Date</th>
        <th>Status</th>
    </tr>

<%
    while (orders != null && orders.next()) {

        hasOrders = true;
%>

    <tr>

        <td>
            <%= orders.getInt("order_id") %>
        </td>

        <td>
            Rs. <%= orders.getDouble("total_amount") %>
        </td>

        <td>
            <%= orders.getTimestamp("order_date") %>
        </td>

        <td class="status">
            <%= orders.getString("status") %>
        </td>

    </tr>

<%
    }

    if (!hasOrders) {
%>

    <tr>
        <td colspan="4">
            No orders found.
        </td>
    </tr>

<%
    }
%>

</table>

<a href="products" class="btn">
    Continue Shopping
</a>

</body>
</html>