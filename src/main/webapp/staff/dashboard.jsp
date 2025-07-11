<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"staff".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect("${pageContext.request.contextPath}/login.jsp");
        return;
    }
%>

<%@ include file="/components/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="p-5 bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Staff Dashboard</h2>
    <p>This is the staff dashboard. You can manage items, and billing here.</p>
</div>
<!-- Bootstrap JS to enable toggle button -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
