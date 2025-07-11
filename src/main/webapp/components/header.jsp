<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<!-- Include Bootstrap Icons -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">

<!-- Sticky Top Navbar with Dark Theme -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top shadow-sm" style="background: linear-gradient(to right, #0f2027, #203a43, #2c5364);">
    <div class="container-fluid px-4">
        <!-- Brand -->
        <a class="navbar-brand fw-bold"><i class="bi bi-book me-2"></i>Pahana Education Bookshop</a>

        <!-- Collapsible menu (for mobile) -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarUserMenu" aria-controls="navbarUserMenu"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Right-side content -->
        <div class="collapse navbar-collapse justify-content-end" id="navbarUserMenu">
            <ul class="navbar-nav">
                <c:if test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <span class="nav-link text-white" style="text-shadow: 1px 1px 2px rgba(255,255,255,0.1);">
                            Welcome, ${sessionScope.user.fullName}
                            (<span class="text-info">${sessionScope.user.role}</span>)
                        </span>
                    </li>
                    <li class="nav-item d-flex align-items-center">
                        <a href="${pageContext.request.contextPath}/logout"
                           class="btn btn-sm btn-danger ms-2">
                            Logout
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<!-- Spacing below fixed navbar -->
<div style="height: 65px;"></div>