<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu | Welcome</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <!-- Bootstrap CSS (local) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">

    <!-- Internal CSS -->
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background-image: url('${pageContext.request.contextPath}/assets/images/wallpaper.jpg'); /* Your wallpaper */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            color: white;
            text-shadow: 1px 1px 2px black;
        }
        .overlay {
            height: 100%;
            width: 100%;
            background-color: rgba(0, 0, 0, 0.5); /* Dim overlay */
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .start-btn {
            margin-top: 10px;
            padding: 10px 20px;
            font-size: 18px;
        }
    </style>
</head>
<body>

<div class="overlay">
    <h1 class="display-3 fw-bold text-center">Pahana Edu Online Billing</h1>
    <p class="lead text-center">Efficient Billing. Smart Management. Trusted Service.</p>
    <!-- Start/Login Button -->
    <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-primary start-btn">Click to Start</a>
</div>

<!-- Bootstrap JS (local) -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
