<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pahana Edu | Forgot Password</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">

    <!-- Login CSS (shared) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">

</head>
<body>

<div class="login-container">
    <!-- Left Wallpaper Section -->
    <div class="image-section"></div>

    <!-- Right Form Section -->
    <div class="form-section">
        <div class="card shadow-lg p-4 login-card animate__animated animate__fadeIn">
            <h4 class="text-center mb-4 text-primary fw-bold">Forgot Password</h4>

            <!-- Error message -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center">${error}</div>
            </c:if>

            <!-- Verification Form -->
            <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                <div class="mb-3">
                    <label class="form-label">Your Email address</label>
                    <input type="email" name="email" class="form-control form-control-lg" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Registered Mobile Number</label>
                    <input type="text" name="phone" class="form-control form-control-lg" required>
                </div>

                <div class="d-grid mb-2">
                    <button type="submit" class="btn btn-primary btn-lg shadow-sm">Verify</button>
                </div>
            </form>

            <!-- To Login -->
            <form action="${pageContext.request.contextPath}/login.jsp" method="get">
                <div class="d-grid">
                    <button type="submit" class="btn btn-secondary btn-lg">Back</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>