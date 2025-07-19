<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    // Role check: only admin allowed
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
    <title>Add New Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Reusable layout -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-3">
        <h2 class="mb-4">Add New Item</h2>

        <!-- Item Form -->
        <form method="post" action="${pageContext.request.contextPath}/admin/items/add"
              class="row g-3 needs-validation" novalidate>

            <!-- Item Name -->
            <div class="col-md-6">
                <label for="name" class="form-label">Item Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
                <div class="invalid-feedback">Item name is required.</div>
            </div>

            <!-- Category -->
            <div class="col-md-6">
                <label for="category" class="form-label">Category</label>
                <select class="form-select" id="category" name="category" required>
                    <option value="">Choose...</option>
                    <option value="Books">Books</option>
                    <option value="Stationery">Stationery</option>
                </select>
                <div class="invalid-feedback">Please select a category.</div>
            </div>

            <!-- Price -->
            <div class="col-md-6">
                <label for="price" class="form-label">Price (LKR)</label>
                <input type="number" class="form-control" id="price" name="price" step="0.01" required>
                <div class="invalid-feedback">Enter a valid price.</div>
            </div>

            <!-- Quantity -->
            <div class="col-md-6">
                <label for="quantity" class="form-label">Quantity</label>
                <input type="number" class="form-control" id="quantity" name="quantity" required>
                <div class="invalid-feedback">Quantity is required.</div>
            </div>

            <!-- Image URL -->
            <div class="col-12">
                <label for="imageUrl" class="form-label">Image URL</label>
                <input type="url" class="form-control" id="imageUrl" name="imageUrl" required>
                <div class="invalid-feedback">Please enter a valid image URL.</div>
            </div>

            <!-- Description -->
            <div class="col-12">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="2" required></textarea>
                <div class="invalid-feedback">Description is required.</div>
            </div>

            <!-- Buttons -->
            <div class="col-12">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save me-1"></i> Add Item
                </button>
                <a href="${pageContext.request.contextPath}/admin/items" class="btn btn-secondary ms-2">Cancel</a>
            </div>
        </form>
    </div>
</main>

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