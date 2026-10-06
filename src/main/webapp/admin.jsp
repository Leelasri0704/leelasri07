<%
    String role = (String) session.getAttribute("role");

    if (role == null || !role.equalsIgnoreCase("ADMIN")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Shopping Mart - Admin Dashboard</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 40px;
        }

        .box {
            width: 600px;
            margin: auto;
            background: white;
            padding: 35px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
            text-align: center;
        }

        h2 {
            margin-bottom: 10px;
        }

        .menu {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
            margin-top: 30px;
        }

        .btn {
            display: block;
            padding: 18px;
            background: #222;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

        .btn:hover {
            background: #444;
        }

        .back {
            display: inline-block;
            margin-top: 25px;
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

    <h2>Admin Dashboard</h2>

    <p>
        Welcome,
        <b><%= session.getAttribute("userName") %></b>
    </p>

    <div class="menu">

        <!-- Product Management -->
        <a href="admin-products" class="btn">
            Product Management
        </a>

        <!-- Customer Management -->
        <a href="admin-customers" class="btn">
            Customer Management
        </a>

        <!-- Order Management -->
        <a href="admin-orders" class="btn">
            Order Management
        </a>

        <!-- Reports -->
        <a href="admin-reports" class="btn">
            Reports
        </a>

    </div>

    <a href="products" class="back">
        Back to Shopping
    </a>

</div>

</body>

</html>