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
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-4">
        <h2>Staff Dashboard</h2>
        <p>This is the staff dashboard. You can manage items and billing here.</p>
    </div>
</main>

<%@ include file="/components/footer.jsp" %>

</body>
</html>