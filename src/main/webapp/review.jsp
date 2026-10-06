<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Shopping Mart - Review</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 40px;
        }

        .box {
            width: 450px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input,
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        textarea {
            height: 100px;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background: #222;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background: #444;
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

    <h2>⭐ Write a Review</h2>

    <form action="review" method="post">

        <label>Product ID</label>

        <input
            type="number"
            name="productId"
            required
        >

        <label>Rating</label>

        <select name="rating" required>

            <option value="">Select Rating</option>
            <option value="5">⭐⭐⭐⭐⭐ 5</option>
            <option value="4">⭐⭐⭐⭐ 4</option>
            <option value="3">⭐⭐⭐ 3</option>
            <option value="2">⭐⭐ 2</option>
            <option value="1">⭐ 1</option>

        </select>

        <label>Comment</label>

        <textarea
            name="comment"
            placeholder="Write your review..."
            required
        ></textarea>

        <button type="submit">
            Submit Review
        </button>

    </form>

    <a href="order-history" class="back">
        Back to Order History
    </a>

</div>

</body>

</html>