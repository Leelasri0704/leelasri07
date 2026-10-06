<%@ page import="java.util.List" %>

<%
    String role = (String) session.getAttribute("role");

    if (role == null || !role.equalsIgnoreCase("ADMIN")) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<String[]> customers =
            (List<String[]>) request.getAttribute("customers");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Customer Management</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 30px;
        }

        .box {
            width: 90%;
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

    <h2>Customer Management</h2>

    <table>

        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
        </tr>

        <%
            if (customers != null) {

                for (String[] customer : customers) {
        %>

        <tr>

            <td><%= customer[0] %></td>
            <td><%= customer[1] %></td>
            <td><%= customer[2] %></td>
            <td><%= customer[3] %></td>

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