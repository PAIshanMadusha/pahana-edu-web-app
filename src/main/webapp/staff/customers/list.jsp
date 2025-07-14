<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>Customer List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Reusable Components -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4">Customer List</h2>

        <!-- Add Button -->
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/staff/customers/add.jsp" class="btn btn-success">
                <i class="bi bi-plus-circle me-1"></i> Add Customer
            </a>
        </div>

        <!-- Success Message -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${sessionScope.successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="successMessage" scope="session"/>
        </c:if>

        <!-- Customer Table -->
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>Account #</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Registered</th>
                <th style="width: 150px;">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${customerList}">
                <tr>
                    <td>${c.accountNumber}</td>
                    <td>${c.fullName}</td>
                    <td>${c.email}</td>
                    <td>${c.phone}</td>
                    <td>${c.address}</td>
                    <td>${c.registeredDate}</td>
                    <td>
                        <div class="d-flex align-items-center">
                            <a href="${pageContext.request.contextPath}/staff/customers/edit?account=${c.accountNumber}" class="btn btn-sm btn-primary" style="width: 70px;">Edit</a>

                            <span style="width: 1px; height: 24px; background-color: #ccc; margin: 0 8px;"></span>

                            <a href="${pageContext.request.contextPath}/staff/customers/delete?account=${c.accountNumber}"
                               class="btn btn-sm btn-danger"
                               style="width: 70px;"
                               onclick="return confirm('Are you sure you want to delete this customer?');">
                                Delete
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty customerList}">
                <tr>
                    <td colspan="7" class="text-center text-muted">No customers found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>

<!-- Reusable Footer -->
<%@ include file="/components/footer.jsp" %>
</body>
</html>