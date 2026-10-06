<%@ page import="java.util.List" %>

<%
    String role = (String) session.getAttribute("role");

    if (role == null || !role.equalsIgnoreCase("ADMIN")) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<String[]> orders =
            (List<String[]>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Order Management</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 30px;
        }

        .box {
            width: 95%;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background: #222;
            color: white;
        }

        .back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #222;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

    </style>

</head>

<body>

<div class="box">

    <h2>Order Management</h2>

    <table>

        <tr>
            <th>Order ID</th>
            <th>Customer Name</th>
            <th>Email</th>
            <th>Total Amount</th>
            <th>Order Date</th>
            <th>Status</th>
        </tr>

        <%
            if (orders != null) {

                for (String[] order : orders) {
        %>

        <tr>

            <td><%= order[0] %></td>
            <td><%= order[1] %></td>
            <td><%= order[2] %></td>
            <td>₹<%= order[3] %></td>
            <td><%= order[4] %></td>
            <td><%= order[5] %></td>

        </tr>

        <%
                }

            }
        %>

    </table>

    <a href="../admin.jsp" class="back">
        Back to Dashboard
    </a>

</div>

</body>

</html>