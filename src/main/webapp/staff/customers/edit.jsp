<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Edit Customer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2>Edit Customer</h2>

        <form action="${pageContext.request.contextPath}/staff/customers/edit" method="post" class="needs-validation" novalidate>
            <input type="hidden" name="accountNumber" value="${customer.accountNumber}"/>

            <div class="mb-3">
                <label for="fullName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="fullName"
                       value="${customer.fullName}" required>
                <div class="invalid-feedback">Full name is required.</div>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${customer.email}" required>
                <div class="invalid-feedback">Please enter a valid email.</div>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone"
                       value="${customer.phone}" required>
                <div class="invalid-feedback">Phone number is required.</div>
            </div>

            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <textarea class="form-control" id="address" name="address" required>${customer.address}</textarea>
                <div class="invalid-feedback">Address is required.</div>
            </div>

            <button type="submit" class="btn btn-primary"><i class="bi bi-save me-2"></i>Update Customer</button>
            <a href="${pageContext.request.contextPath}/staff/customers" class="btn btn-secondary ms-2">Cancel</a>
        </form>
    </div>
</main>

<%@ include file="/components/footer.jsp" %>

<script>
    // Bootstrap form validation
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