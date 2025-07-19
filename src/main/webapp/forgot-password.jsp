<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <div class="col-md-6 offset-md-3">
        <div class="card p-4">
            <h4 class="mb-3 text-center">Forgot Password</h4>

            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                <div class="mb-3">
                    <label>Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Registered Mobile Number</label>
                    <input type="text" name="phone" class="form-control" required>
                </div>

                <div class="d-grid mb-2">
                    <button type="submit" class="btn btn-primary">Verify</button>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/login.jsp" method="get">
                <div class="d-grid">
                    <button type="submit" class="btn btn-secondary">Back</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>