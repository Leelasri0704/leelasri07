<%@ page import="java.util.List" %>
<%@ page import="com.shoppingmart.model.Product" %>

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
    <title>Admin - Product Management</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 30px;
        }

        .box {
            width: 1100px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
        }

        .add-btn {
            display: inline-block;
            padding: 12px 20px;
            background: green;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 15px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
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

        input[type="number"] {
            width: 70px;
            padding: 7px;
        }

        button {
            padding: 7px 12px;
            background: #222;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .delete-btn {
            background: #d32f2f;
        }

        .back {
            display: inline-block;
            padding: 10px 18px;
            background: #222;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

    </style>

</head>

<body>

<div class="box">

    <h2>Product Management</h2>

    <a href="add-product.jsp" class="add-btn">
        + Add New Product
    </a>

    <table>

        <tr>
            <th>ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Current Stock</th>
            <th>Update Stock</th>
            <th>Remove Product</th>
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

                <form action="update-stock" method="post">

                    <input
                        type="hidden"
                        name="productId"
                        value="<%= product.getProductId() %>"
                    >

                    <input
                        type="number"
                        name="stock"
                        value="<%= product.getStock() %>"
                        min="0"
                        required
                    >

                    <button type="submit">
                        Update
                    </button>

                </form>

            </td>

            <td>

                <form
                    action="delete-product"
                    method="post"
                    onsubmit="return confirm('Are you sure you want to remove this product?');"
                >

                    <input
                        type="hidden"
                        name="productId"
                        value="<%= product.getProductId() %>"
                    >

                    <button
                        type="submit"
                        class="delete-btn"
                    >
                        Remove
                    </button>

                </form>

            </td>

        </tr>

<%
        }

    } else {
%>

        <tr>
            <td colspan="6">
                No products found.
            </td>
        </tr>

<%
    }
%>

    </table>

    <a href="admin.jsp" class="back">
        Back to Dashboard
    </a>

</div>

</body>
</html>