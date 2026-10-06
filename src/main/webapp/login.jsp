<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Mart - Login</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            text-align: center;
            padding-top: 80px;
        }

        .box {
            width: 350px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        input {
            width: 90%;
            padding: 12px;
            margin: 8px;
        }

        button {
            width: 95%;
            padding: 12px;
            background: #222;
            color: white;
            border: none;
            cursor: pointer;
        }

        a {
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="box">

    <h2>Shopping Mart</h2>
    <h3>Login</h3>

    <form action="login" method="post">

        <input type="email"
               name="email"
               placeholder="Enter Email"
               required>

        <input type="password"
               name="password"
               placeholder="Enter Password"
               required>

        <button type="submit">Login</button>

    </form>

    <p>
        Don't have an account?
        <a href="register.jsp">Register</a>
    </p>

</div>

</body>
</html>