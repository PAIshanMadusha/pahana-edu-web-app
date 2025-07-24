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
    <title>Pahana Edu | Manage Users</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Reusable Components -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4"><i class="bi bi-shield-lock-fill me-2 text-primary"></i>Manage Users</h2>

        <!-- Add User Button -->
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/admin/users/add.jsp" class="btn btn-primary">
                <i class="bi bi-plus-circle me-1"></i> Add New User
            </a>
        </div>

        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${sessionScope.successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="successMessage" scope="session" />
        </c:if>

        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${sessionScope.errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="errorMessage" scope="session" />
        </c:if>

        <!-- Users Table -->
        <table class="table table-bordered table-hover table-striped">
            <thead class="table-dark">
            <tr>
                <th>Email</th>
                <th>Full Name</th>
                <th>Role</th>
                <th>Phone</th>
                <th style="width: 150px;">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="u" items="${userList}">
                <tr>
                    <td>${u.email}</td>
                    <td>${u.fullName}</td>
                    <td class="text-capitalize">${u.role}</td>
                    <td>${u.phone}</td>
                    <td>
                        <div class="d-flex align-items-center">
                            <a href="${pageContext.request.contextPath}/admin/users/edit?email=${u.email}" class="btn btn-sm btn-primary" style="width:70px;">
                                Edit
                            </a>

                            <!-- Divider -->
                            <span style="width:1px; height:24px; background-color:#ccc; margin: 0 8px;"></span>

                            <a href="${pageContext.request.contextPath}/admin/users/delete?email=${u.email}" class="btn btn-sm btn-danger" style="width:70px;" onclick="return confirm('Are you sure you want to delete this user?');">
                                Delete
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty userList}">
                <tr>
                    <td colspan="5" class="text-center text-muted">No users found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>

<%@ include file="/components/footer.jsp" %>
</body>
<!-- Bootstrap JS Bundle (required for alert dismissing) -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</html>
