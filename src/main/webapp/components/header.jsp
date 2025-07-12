<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">

<!-- Sticky Header -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top shadow-sm py-2"
     style="background: linear-gradient(to right, #0f2027, #203a43, #2c5364); z-index: 1050;">
    <div class="container-fluid d-flex justify-content-between align-items-center px-4">

        <!-- Toggle + Brand (Grouped together) -->
        <div class="d-flex align-items-center me-auto">
            <!-- Sidebar Toggle -->
            <button class="btn btn-outline-light me-4" id="sidebarToggle">
                <i class="bi bi-list"></i>
            </button>

            <!-- Brand -->
            <a class="navbar-brand fw-bold mb-0"><i class="bi bi-book me-2"></i>Pahana Education Bookshop</a>
        </div>

        <!-- Right Side -->
        <div class="d-none d-lg-flex align-items-center">
            <ul class="navbar-nav">
                <c:if test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <span class="nav-link text-white fw-bold">
                            Welcome, ${sessionScope.user.fullName}
                            (<span class="text-info">${sessionScope.user.role}</span>)
                        </span>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger ms-2">Logout</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<!-- Space below navbar -->
<div style="height: 65px;"></div>
