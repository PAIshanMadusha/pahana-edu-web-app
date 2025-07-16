<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.pahanaeduwebapp.model.Bill" %>
<%@ page import="java.util.List" %>
<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"staff".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<%
    List<Bill> bills = (List<Bill>) request.getAttribute("bills");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Billing History</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-4">
    <h2 class="mb-4">Billing History</h2>

    <c:if test="${empty bills}">
        <div class="alert alert-warning">No bills found.</div>
    </c:if>

        <!-- Success Message -->
        <c:if test="${not empty sessionScope.success}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${sessionScope.success}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="success" scope="session"/>
        </c:if>


        <c:if test="${not empty bills}">
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
            <tr>
                <th>Bill ID</th>
                <th>Customer Account</th>
                <th>Date</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${bills}">
                <tr>
                    <td>${b.billId}</td>
                    <td>${b.customerAccountNumber}</td>
                    <td>${b.createdAt}</td>
                    <td>Rs. ${b.totalAmount}</td>
                    <td class="text-center align-middle">
                        <div class="d-inline-flex align-items-center justify-content-center">
                            <form method="post" action="${pageContext.request.contextPath}/staff/billing/view" class="me-2">
                                <input type="hidden" name="billId" value="${b.billId}" />
                                <button type="submit" class="btn btn-sm btn-primary" style="width: 70px;">View</button>
                            </form>

                            <div style="width: 1px; height: 24px; background-color: #ccc;"></div>

                            <form method="post" action="${pageContext.request.contextPath}/staff/billing/delete"
                                  onsubmit="return confirm('Are you sure you want to delete this bill?');" class="ms-2">
                                <input type="hidden" name="billId" value="${b.billId}" />
                                <button type="submit" class="btn btn-sm btn-danger" style="width: 70px;">Remove</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
    </div>
</main>
<%@ include file="/components/footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>