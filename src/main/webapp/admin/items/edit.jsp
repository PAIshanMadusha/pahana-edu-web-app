<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    com.example.pahanaeduwebapp.model.Item item =
            (com.example.pahanaeduwebapp.model.Item) request.getAttribute("item");

    if (item == null) {
        response.sendRedirect(request.getContextPath() + "/admin/items");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Reusable Components -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-4">
        <h2 class="mb-4">Edit Item</h2>

        <form method="post" action="${pageContext.request.contextPath}/admin/items/edit" class="row g-3 needs-validation" novalidate>
            <!-- Hidden field to keep the item ID -->
            <input type="hidden" name="itemId" value="${item.itemId}" />

            <div class="col-md-6">
                <label for="name" class="form-label">Item Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${item.name}" required>
                <div class="invalid-feedback">Name is required.</div>
            </div>

            <div class="col-md-6">
                <label for="category" class="form-label">Category</label>
                <select class="form-select" id="category" name="category" required>
                    <option value="book" ${item.category == 'book' ? 'selected' : ''}>Book</option>
                    <option value="stationery" ${item.category == 'stationery' ? 'selected' : ''}>Stationery</option>
                </select>
                <div class="invalid-feedback">Category is required.</div>
            </div>

            <div class="col-md-6">
                <label for="price" class="form-label">Price</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" value="${item.price}" required>
                <div class="invalid-feedback">Enter a valid price.</div>
            </div>

            <div class="col-md-6">
                <label for="quantity" class="form-label">Quantity</label>
                <input type="number" class="form-control" id="quantity" name="quantity" value="${item.quantity}" required>
                <div class="invalid-feedback">Enter quantity.</div>
            </div>

            <div class="col-md-12">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="2">${item.description}</textarea>
            </div>

            <div class="col-md-12">
                <label for="imageUrl" class="form-label">Image URL</label>
                <input type="text" class="form-control" id="imageUrl" name="imageUrl" value="${item.imageUrl}">
            </div>

            <div class="col-12">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save me-1"></i> Update Item
                </button>
                <a href="${pageContext.request.contextPath}/admin/items" class="btn btn-secondary ms-2">Cancel</a>
            </div>
        </form>
    </div>
</main>

<!-- Footer -->
<%@ include file="/components/footer.jsp" %>

<!-- Bootstrap validation -->
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