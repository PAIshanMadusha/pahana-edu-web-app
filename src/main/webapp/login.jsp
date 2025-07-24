<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pahana Edu | Login</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">

    <!-- Login CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body>

<div class="login-container">
    <!-- Left Image Section -->
    <div class="image-section"></div>

    <!-- Right Form Section -->
    <div class="form-section">
        <div class="card shadow-lg p-4 login-card animate__animated animate__fadeIn">

            <!-- Logo (Optional) -->
            <div class="text-center mb-3">
                <img src="${pageContext.request.contextPath}/assets/images/favicon.png" alt="Logo" style="height: 60px;">
            </div>

            <h3 class="text-center mb-4 fw-bold text-primary">Staff / Admin Login</h3>

            <!-- Error Message -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center">${error}</div>
            </c:if>

            <!-- Login Form -->
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Your Email address</label>
                    <input type="email" name="email" id="email" class="form-control form-control-lg" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Your Password</label>
                    <input type="password" name="password" id="password" class="form-control form-control-lg" required>
                </div>

                <div class="d-grid mb-3">
                    <button type="submit" class="btn btn-primary btn-lg shadow-sm">Login</button>
                </div>

                <div class="text-center">
                    <a href="forgot-password.jsp" class="text-decoration-none link-primary">Forgot password?</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>