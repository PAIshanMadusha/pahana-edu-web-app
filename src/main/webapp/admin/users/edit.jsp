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
    <title>Pahana Edu | Edit User</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Header & Sidebar -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4"><i class="bi bi-shield-lock-fill me-2 text-primary"></i>Edit User</h2>

        <form action="${pageContext.request.contextPath}/admin/users/edit" method="post" >
            <!-- Email (readonly) -->
            <div class="mb-3">
                <label for="email" class="form-label">Email Address (readonly)</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${userToEdit.email}" readonly>
            </div>

            <!-- Full Name -->
            <div class="mb-3">
                <label for="fullName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="fullName"
                       value="${userToEdit.fullName}" required>
            </div>

            <!-- Role -->
            <div class="mb-3">
                <label for="role" class="form-label">User Role</label>
                <select class="form-select" id="role" name="role" required>
                    <option value="admin" ${userToEdit.role eq 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="staff" ${userToEdit.role eq 'staff' ? 'selected' : ''}>Staff</option>
                </select>
            </div>

            <!-- Phone -->
            <div class="mb-3">
                <label for="phone" class="form-label">Phone (for recovery)</label>
                <input type="text" class="form-control" id="phone" name="phone"
                       value="${userToEdit.phone}" required>
            </div>

            <!-- Buttons -->
            <button type="submit" class="btn btn-primary"><i class="bi bi-save me-1"></i> Update User</button>
            <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-secondary ms-2">Cancel</a>
        </form>
    </div>
</main>

<%@ include file="/components/footer.jsp" %>
</body>
</html>