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
    <title>Pahana Edu | Staff Dashboard</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2 mb-4">
        <h2 class="mb-4"><i class="bi bi-person-lines-fill me-2 text-primary"></i>Staff Dashboard</h2>

        <div class="row g-4">

            <!-- Add Customer -->
            <div class="col-md-4">
                <div class="card shadow-sm border-0 h-100 bg-primary-subtle">
                    <div class="card-body">
                        <h5 class="card-title"><i class="bi bi-person-plus-fill me-2"></i> Add Customer</h5>
                        <p class="card-text">Register a new customer and generate an account number.</p>
                        <a href="${pageContext.request.contextPath}/staff/customers/add" class="btn btn-primary" style="width: 70px;">Go</a>
                    </div>
                </div>
            </div>

            <!-- View Customers -->
            <div class="col-md-4">
                <div class="card shadow-sm border-0 h-100 bg-success-subtle">
                    <div class="card-body">
                        <h5 class="card-title"><i class="bi bi-search me-2"></i> Manage Customers</h5>
                        <p class="card-text">View, edit, delete or search for registered customer accounts.</p>
                        <a href="${pageContext.request.contextPath}/staff/customers" class="btn btn-primary" style="width: 70px;">Go</a>
                    </div>
                </div>
            </div>

            <!-- View Items -->
            <div class="col-md-4">
                <div class="card shadow-sm border-0 h-100 bg-info-subtle">
                    <div class="card-body">
                        <h5 class="card-title"><i class="bi bi-journal-bookmark-fill me-2"></i> View Items</h5>
                        <p class="card-text">Search books and stationery in stock, or check what is available.</p>
                        <a href="${pageContext.request.contextPath}/staff/items" class="btn btn-primary" style="width: 70px;">View</a>
                    </div>
                </div>
            </div>

            <!-- Generate Bill -->
            <div class="col-md-4">
                <div class="card shadow-sm border-0 h-100 bg-warning-subtle">
                    <div class="card-body">
                        <h5 class="card-title"><i class="bi bi-calculator-fill me-2"></i> Generate Bill</h5>
                        <p class="card-text">Create a bill by selecting an existing customer and desired items.</p>
                        <a href="${pageContext.request.contextPath}/staff/billing/generate" class="btn btn-primary" style="width: 70px;">Go</a>
                    </div>
                </div>
            </div>

            <!-- Billing History -->
            <div class="col-md-4">
                <div class="card shadow-sm border-0 h-100 bg-danger-subtle">
                    <div class="card-body">
                        <h5 class="card-title"><i class="bi bi-receipt-cutoff me-2"></i> Billing History</h5>
                        <p class="card-text">View, print, or remove customer previously generated bills.</p>
                        <a href="${pageContext.request.contextPath}/staff/billing/history" class="btn btn-primary" style="width: 70px;">View</a>
                    </div>
                </div>
            </div>

            <!-- Help -->
            <div class="col-md-4">
                <div class="card shadow-sm border-0 h-100 bg-secondary-subtle">
                    <div class="card-body">
                        <h5 class="card-title"><i class="bi bi-info-circle-fill me-2"></i> Help Guide</h5>
                        <p class="card-text">Access instructions and tips for using the staff features.</p>
                        <a href="${pageContext.request.contextPath}/staff/help.jsp" class="btn btn-primary" style="width: 70px;">View</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</main>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        if (localStorage.getItem('sidebarExpanded') === 'true') {
            document.body.classList.add('sidebar-expanded');
        }
    });
</script>

<%@ include file="/components/footer.jsp" %>

</body>
</html>