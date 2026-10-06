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
    <title>Add Product - Admin</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 50px;
        }

        .box {
            width: 400px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
        }

        input {
            width: 92%;
            padding: 12px;
            margin: 10px 0;
        }

        button {
            width: 100%;
            padding: 12px;
            background: #222;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #222;
        }
    </style>
</head>

<body>

<div class="box">

    <h2>Add New Product</h2>

    <form action="add-product" method="post">

        <input
            type="text"
            name="productName"
            placeholder="Product Name"
            required
        >

        <input
            type="number"
            name="price"
            placeholder="Price"
            step="0.01"
            min="0"
            required
        >

        <input
            type="number"
            name="stock"
            placeholder="Stock"
            min="0"
            required
        >

        <button type="submit">
            Add Product
        </button>

    </form>

    <a href="admin-products" class="back">
        Back to Product Management
    </a>

</div>

</body>
</html>