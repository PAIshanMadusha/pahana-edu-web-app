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
    <meta charset="UTF-8" />
    <title>Audit Logs</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4">Audit Logs</h2>

        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${sessionScope.successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="successMessage" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${sessionScope.errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>User Email</th>
                <th>Action</th>
                <th>Details</th>
                <th>Timestamp</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="log" items="${auditLogs}">
                <tr>
                    <td>${log.userEmail}</td>
                    <td>${log.action}</td>
                    <td>${log.details}</td>
                    <td>${log.timestamp}</td>
                    <td class="d-flex justify-content-center">
                        <form method="post" action="${pageContext.request.contextPath}/admin/auditLogs/delete"
                              onsubmit="return confirm('Are you sure you want to delete this log?');">
                            <input type="hidden" name="logId" value="${log.id}" />
                            <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty auditLogs}">
                <tr>
                    <td colspan="4" class="text-center text-muted">No audit logs found.</td>
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