<%
    String role = (String) session.getAttribute("role");

    if (role == null || !role.equalsIgnoreCase("ADMIN")) {
        response.sendRedirect("login.jsp");
        return;
    }

    Integer totalProducts =
        (Integer) request.getAttribute("totalProducts");

    Integer totalCustomers =
        (Integer) request.getAttribute("totalCustomers");

    Integer totalOrders =
        (Integer) request.getAttribute("totalOrders");

    Double totalSales =
        (Double) request.getAttribute("totalSales");

    if (totalProducts == null) totalProducts = 0;
    if (totalCustomers == null) totalCustomers = 0;
    if (totalOrders == null) totalOrders = 0;
    if (totalSales == null) totalSales = 0.0;
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Shopping Mart - Reports</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 40px;
        }

        .box {
            width: 800px;
            margin: auto;
            background: white;
            padding: 35px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
        }

        .reports {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .card {
            padding: 25px;
            background: #f5f5f5;
            border-radius: 8px;
            text-align: center;
            border: 1px solid #ddd;
        }

        .card h3 {
            margin-bottom: 10px;
        }

        .value {
            font-size: 28px;
            font-weight: bold;
        }

        .back {
            display: inline-block;
            margin-top: 30px;
            padding: 12px 25px;
            background: #222;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

    </style>

</head>

<body>

<div class="box">

    <h2>Shopping Mart Reports</h2>

    <div class="reports">

        <div class="card">

            <h3>Total Products</h3>

            <div class="value">
                <%= totalProducts %>
            </div>

        </div>

        <div class="card">

            <h3>Total Customers</h3>

            <div class="value">
                <%= totalCustomers %>
            </div>

        </div>

        <div class="card">

            <h3>Total Orders</h3>

            <div class="value">
                <%= totalOrders %>
            </div>

        </div>

        <div class="card">

            <h3>Total Sales</h3>

            <div class="value">
                Rs. <%= totalSales %>
            </div>

        </div>

    </div>

    <a href="admin.jsp" class="back">
        Back to Dashboard
    </a>

</div>

</body>

</html>