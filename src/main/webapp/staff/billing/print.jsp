<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.pahanaeduwebapp.model.Bill" %>

<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"staff".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<%
    Bill bill = (Bill) request.getAttribute("bill");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pahana Edu | Print Bill</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
<div class="container mt-2">
    <h2 class="mb-4"><i class="bi bi-person-lines-fill me-2 text-primary"></i>Bill Details</h2>

    <table class="table table-bordered">
        <tr><th>Bill ID</th><td>${bill.billId}</td></tr>
        <tr><th>Customer Account</th><td>${bill.customerAccountNumber}</td></tr>
        <tr><th>Customer Email</th><td>${customerEmail}</td></tr>
        <tr><th>Created At</th><td>${bill.createdAt}</td></tr>
        <tr><th>Total</th><td>Rs. ${bill.totalAmount}</td></tr>
    </table>

    <h4 class="mt-4">Items</h4>
    <table class="table table-striped">
        <thead class="table-dark">
        <tr>
            <th>Item ID</th>
            <th>Name</th>
            <th>Unit Price</th>
            <th>Qty</th>
            <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${bill.items}">
            <tr>
                <td>${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.unitPrice}</td>
                <td>${item.quantity}</td>
                <td>${item.subtotal}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="mt-3">
        <button class="btn btn-primary" onclick="window.print()">Print</button>
        <a href="${pageContext.request.contextPath}/staff/billing/history" class="btn btn-secondary">Back to History</a>
    </div>
</div>
</main>
<%@ include file="/components/footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>