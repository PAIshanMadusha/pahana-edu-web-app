<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<!-- Preload Logic -->
<script>
    if (localStorage.getItem('sidebarExpanded') === 'true') {
        document.addEventListener('DOMContentLoaded', function () {
            document.body.classList.add('sidebar-expanded');
        });
    }
</script>

<style>
    html.sidebar-preload .sidebar {
        width: 250px !important;
    }
    html.sidebar-preload .sidebar-text {
        display: inline !important;
    }
    html.sidebar-preload body {
        margin-left: 250px !important;
    }
    body {
        visibility: visible; /* Show body after preload */
    }
</style>

<!-- Bootstrap Icons & Custom CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">

<!-- Sticky Header -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top shadow-sm py-2"
     style="background: linear-gradient(to right, #0f2027, #203a43, #2c5364); z-index: 1050;">
    <div class="container-fluid d-flex justify-content-between align-items-center px-4">

        <!-- Toggle + Brand -->
        <div class="d-flex align-items-center me-auto">
            <button class="btn btn-outline-light me-4" id="sidebarToggle">
                <i class="bi bi-list"></i>
            </button>
            <a class="navbar-brand fw-bold mb-0"><i class="bi bi-book me-2"></i>Pahana Edu Online Billing</a>
        </div>

        <!-- Right-side user info -->
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
                        <a href="${pageContext.request.contextPath}/logout"
                           class="btn btn-danger ms-2"
                           onclick="return confirm('Are you sure you want to logout?');">
                            Logout
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<!-- Space below navbar -->
<div style="height: 65px;"></div>