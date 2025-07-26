<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"staff".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pahana Edu | Add New Customer</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px; background-color: #f8f9fa;">
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
            <div class="card-body p-2">
                <h2 class="mb-4"><i class="bi bi-person-lines-fill me-2 text-primary"></i>Add New Customer</h2>

                <form method="post" action="${pageContext.request.contextPath}/staff/customers/add" class="row g-4 needs-validation" novalidate>

                    <div class="col-md-6">
                        <label for="fullName" class="form-label">Full Name</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                            <input type="text" class="form-control" id="fullName" name="fullName" required>
                            <div class="invalid-feedback">Please provide a full name.</div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="email" class="form-label">Email</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-envelope-fill"></i></span>
                            <input type="email" class="form-control" id="email" name="email" required>
                            <div class="invalid-feedback">Enter a valid email address.</div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="phone" class="form-label">Phone</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-telephone-fill"></i></span>
                            <input type="text" class="form-control" id="phone" name="phone" required>
                            <div class="invalid-feedback">Phone number is required.</div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="address" class="form-label">Address</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-geo-alt-fill"></i></span>
                            <textarea class="form-control" id="address" name="address" rows="1" required></textarea>
                            <div class="invalid-feedback">Address is required.</div>
                        </div>
                    </div>

                    <div class="col-12 d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary px-4 me-2">
                            <i class="bi bi-save me-2"></i>Add Customer
                        </button>
                        <a href="${pageContext.request.contextPath}/staff/customers" class="btn btn-secondary px-4">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
</main>

<%@ include file="/components/footer.jsp" %>

<!-- Bootstrap form validation -->
<script>
    (() => {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>
</body>
</html>
