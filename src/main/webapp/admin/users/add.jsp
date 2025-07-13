<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Reusable Components -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4">Add New User</h2>

        <!-- User Form -->
        <form method="post" action="${pageContext.request.contextPath}/admin/users/add" class="row g-3 needs-validation" novalidate>

            <div class="col-md-6">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" class="form-control" id="email" name="email" required>
                <div class="invalid-feedback">Please provide a valid email.</div>
            </div>

            <div class="col-md-6">
                <label for="role" class="form-label">User Role</label>
                <select class="form-select" id="role" name="role" required>
                    <option value="">Choose...</option>
                    <option value="admin">Admin</option>
                    <option value="staff">Staff</option>
                </select>
                <div class="invalid-feedback">Please select a role.</div>
            </div>

            <div class="col-md-6">
                <label for="fullName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="fullName" required>
                <div class="invalid-feedback">Full name is required.</div>
            </div>

            <div class="col-md-6">
                <label for="password" class="form-label">User Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <div class="invalid-feedback">Please enter a password.</div>
            </div>

            <div class="col-md-6">
                <label for="phone" class="form-label">Phone (for recovery)</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
                <div class="invalid-feedback">Phone number is required.</div>
            </div>

            <div class="col-12">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save me-1"></i> Add User
                </button>
                <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-secondary ms-2">Cancel</a>
            </div>
        </form>
    </div>
</main>

<!-- Reusable Footer -->
<%@ include file="/components/footer.jsp" %>

<!-- Bootstrap validation script -->
<script>
    (() => {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', e => {
                if (!form.checkValidity()) {
                    e.preventDefault();
                    e.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>
</body>
</html>
