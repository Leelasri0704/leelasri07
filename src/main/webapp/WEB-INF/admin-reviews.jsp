<%@ page import="java.sql.ResultSet" %>

<%
    String role = (String) session.getAttribute("role");

    if (role == null || !role.equalsIgnoreCase("ADMIN")) {
        response.sendRedirect("../login.jsp");
        return;
    }

    ResultSet reviews =
        (ResultSet) request.getAttribute("reviews");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Shopping Mart - Review Management</title>

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
    width: 90%;
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

.rating {
    color: #e6a800;
    font-weight: bold;
}

.back {
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

.back:hover {
    background: #444;
}

</style>

</head>

<body>

<h2>⭐ Review Management</h2>

<table>

<tr>
    <th>Review ID</th>
    <th>Customer</th>
    <th>Product</th>
    <th>Rating</th>
    <th>Comment</th>
</tr>

<%
    boolean hasReviews = false;

    while (reviews != null && reviews.next()) {

        hasReviews = true;
%>

<tr>

<td>
    <%= reviews.getInt("review_id") %>
</td>

<td>
    <%= reviews.getString("name") %>
</td>

<td>
    <%= reviews.getString("product_name") %>
</td>

<td class="rating">
    <%= reviews.getInt("rating") %> ⭐
</td>

<td>
    <%= reviews.getString("comment") %>
</td>

</tr>

<%
    }

    if (!hasReviews) {
%>

<tr>
    <td colspan="5">
        No reviews found.
    </td>
</tr>

<%
    }
%>

</table>

<a href="../admin.jsp" class="back">
    Back to Dashboard
</a>

</body>
</html>