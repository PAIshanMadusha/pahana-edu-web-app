<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect("${pageContext.request.contextPath}/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<!-- Reusable Header -->
<%@ include file="/components/header.jsp" %>

<main class="flex-grow-1">
    <div class="container mt-5">
        <h2 class="mb-4">Admin Dashboard</h2>
        <p>This is the admin dashboard. You can manage users, items, and billing here.</p>
    </div>
</main>

<!-- Reusable Footer -->
<%@ include file="/components/footer.jsp" %>
</body>
</html>
