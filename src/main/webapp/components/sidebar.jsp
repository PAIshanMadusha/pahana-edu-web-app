<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<%
        String currentPath = request.getServletPath();
%>

<div id="sidebar" class="text-white position-fixed d-flex flex-column p-2 sidebar"
     style="top: 55px; bottom: 0; left: 0; transition: width 0.3s; z-index: 1030; background: linear-gradient(to left, #0f2027, #203a43, #2c5364);">

    <hr class="sidebar-divider">

    <!-- Navigation -->
    <ul class="nav nav-pills flex-column mb-auto">

        <!-- Admin Role Links -->
        <c:if test="${user.role eq 'admin'}">

            <!-- Dashboard -->
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/admin/dashboard.jsp"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.endsWith("dashboard.jsp") ? "active" : "" %>">
                    <i class="bi bi-house me-2 fs-5"></i>
                    <span class="sidebar-text">Dashboard</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/admin/users"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/admin/users") ? "active" : "" %>">
                    <i class="bi bi-people me-2"></i>
                    <span class="sidebar-text">Manage Users</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/items"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/admin/items") ? "active" : "" %>">
                    <i class="bi bi-journal-plus me-2"></i>
                    <span class="sidebar-text">Manage Items</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/billing"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/admin/billing") ? "active" : "" %>">
                    <i class="bi bi-calculator me-2"></i>
                    <span class="sidebar-text">Generate Bills</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/reports"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/admin/reports") ? "active" : "" %>">
                    <i class="bi bi-bar-chart-line me-2"></i>
                    <span class="sidebar-text">Reports</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/logs"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/admin/logs") ? "active" : "" %>">
                    <i class="bi bi-clock-history me-2"></i>
                    <span class="sidebar-text">Audit Logs</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/help"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/help") ? "active" : "" %>">
                    <i class="bi bi-question-circle me-2"></i>
                    <span class="sidebar-text">Help</span>
                </a>
            </li>
        </c:if>

        <!-- Staff Role Links -->
        <c:if test="${user.role eq 'staff'}">

            <!-- Dashboard -->
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/staff/dashboard.jsp"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.endsWith("dashboard.jsp") ? "active" : "" %>">
                    <i class="bi bi-house me-2 fs-5"></i>
                    <span class="sidebar-text">Dashboard</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/staff/customers/add"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/staff/customers/add") ? "active" : "" %>">
                    <i class="bi bi-person-plus me-2"></i>
                    <span class="sidebar-text">Add Customer</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/staff/customers"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/staff/customers") ? "active" : "" %>">
                    <i class="bi bi-search me-2"></i>
                    <span class="sidebar-text">View Customers</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/staff/items"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/staff/items") ? "active" : "" %>">
                    <i class="bi bi-journal-plus me-2"></i>
                    <span class="sidebar-text">Manage Items</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/staff/billing"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/staff/billing") ? "active" : "" %>">
                    <i class="bi bi-calculator me-2"></i>
                    <span class="sidebar-text">Generate Bill</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/staff/billing/history"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/staff/billing/history") ? "active" : "" %>">
                    <i class="bi bi-receipt-cutoff me-2"></i>
                    <span class="sidebar-text">Billing History</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/help"
                   class="nav-link text-white d-flex align-items-center <%= currentPath.contains("/help") ? "active" : "" %>">
                    <i class="bi bi-info-circle me-2"></i>
                    <span class="sidebar-text">Help</span>
                </a>
            </li>
        </c:if>
    </ul>
    <hr class="sidebar-divider">

    <div class="text-white small">
        <div class="collapsed-only mb-1 mb-1 w-100 d-flex align-items-center justify-content-between">
            <!-- Role badge (always visible) -->
            <span class="badge bg-info text-dark text-capitalize">${user.role}</span>
        </div>
        <div class="mb-2">
            <strong class="sidebar-text">${user.fullName}</strong><br>
        </div>
        <div class="mb-2">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger mt-2 sidebar-text">Logout</a>
        </div>
    </div>
</div>
