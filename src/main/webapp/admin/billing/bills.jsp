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
    <title>Manage Bills</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4">View Bills</h2>

        <!-- Alert Messages -->
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

        <!-- Bills Table -->
        <table class="table table-bordered table-hover table-striped">
            <thead class="table-dark">
            <tr>
                <th>Bill ID</th>
                <th>Customer Account No</th>
                <th>Total Amount</th>
                <th>Created At</th>
                <th style="width: 150px;">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${bills}">
                <tr>
                    <td>${b.billId}</td>
                    <td>${b.customerAccountNumber}</td>
                    <td>LKR ${b.totalAmount}</td>
                    <td>${b.createdAt}</td>
                    <td>
                        <div class="d-flex align-items-center">
                            <form method="post" action="${pageContext.request.contextPath}/admin/billing/view">
                                <input type="hidden" name="billId" value="${b.billId}" />
                                <button type="submit" class="btn btn-sm btn-primary" style="width: 70px;">View</button>
                            </form>

                            <span style="width:1px; height:24px; background-color:#ccc; margin: 0 8px;"></span>

                            <form method="post" action="${pageContext.request.contextPath}/admin/bills/delete" onsubmit="return confirm('Are you sure you want to delete this bill?');">
                                <input type="hidden" name="billId" value="${b.billId}" />
                                <button type="submit" class="btn btn-sm btn-danger" style="width: 70px;">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty bills}">
                <tr>
                    <td colspan="5" class="text-center text-muted">No bills found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>

<%@ include file="/components/footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>