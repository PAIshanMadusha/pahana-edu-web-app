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
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="p-5">
<div class="container">
    <h2>Welcome, Staff: ${user.fullName}</h2>
    <p>This is the staff dashboard.</p>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
</div>
</body>
</html>
