<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>

<%
    // ✅ Basic Role Check (only admin can access this)
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
    <title>Manage Items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- ✅ Reusable Header and Sidebar -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4">Item Management</h2>

        <!-- ✅ Add New Item Button -->
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/admin/items/add.jsp" class="btn btn-success">
                <i class="bi bi-plus-circle me-1"></i> Add New Item
            </a>
        </div>

        <!-- ✅ Success Message After Add/Edit/Delete -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${sessionScope.successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="successMessage" scope="session" />
        </c:if>

        <!-- ✅ Item List Table -->
        <table class="table table-bordered table-hover table-striped">
            <thead class="table-dark">
            <tr>
                <th>Item Name</th>
                <th>Category</th>
                <th>Price (LKR)</th>
                <th>Quantity</th>
                <th>Image</th>
                <th style="width: 150px;">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${itemList}">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.category}</td>
                    <td>Rs. <fmt:formatNumber value="${item.price}" type="number" minFractionDigits="2" /></td>
                    <td>${item.quantity}</td>
                    <td>
                        <img src="${item.imageUrl}" alt="Image" width="50" height="50"
                             style="object-fit: cover; border-radius: 4px;">
                    </td>
                    <td>
                        <div class="d-flex align-items-center">
                            <a href="${pageContext.request.contextPath}/admin/items/edit?itemId=${item.itemId}"
                               class="btn btn-sm btn-primary" style="width: 70px;">Edit</a>

                            <span style="width: 1px; height: 24px; background-color: #ccc; margin: 0 8px;"></span>

                            <a href="${pageContext.request.contextPath}/admin/items/delete?itemId=${item.itemId}"
                               class="btn btn-sm btn-danger" style="width: 70px;"
                               onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty itemList}">
                <tr>
                    <td colspan="6" class="text-center text-muted">No items found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>

<%@ include file="/components/footer.jsp" %>

<!-- ✅ Bootstrap JS (for alert and responsiveness) -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>