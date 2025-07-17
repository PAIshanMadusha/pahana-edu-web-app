<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>

<%
    com.example.pahanaeduwebapp.model.User user =
            (com.example.pahanaeduwebapp.model.User) session.getAttribute("user");

    if (user == null || !"staff".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">

    <!-- CSS Inline -->
    <style>
        .item-card {
            transition: transform 0.2s ease;
        }

        .item-card:hover {
            transform: scale(1.02);
        }

        .item-img {
            height: 180px;
            object-fit: cover;
        }

        .truncate-text {
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }
    </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Reusable Components -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4">Available Items</h2>

        <!-- Search Bar -->
        <div class="mb-3">
            <input type="text" class="form-control" id="itemSearchInput"
                   placeholder="Search by name or category...">
        </div>

        <!-- Card Grid -->
        <div class="row" id="itemCardContainer">
            <c:forEach var="item" items="${items}">
                <div class="col-md-4 col-lg-3 mb-4 item-card-wrapper">
                    <div class="card item-card h-100 shadow-sm">
                        <c:if test="${not empty item.imageUrl}">
                            <img src="${item.imageUrl}" class="card-img-top item-img" alt="${item.name}">
                        </c:if>
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${item.name}</h5>
                            <h6 class="text-muted mb-1">${item.category}</h6>
                            <p class="card-text text-muted truncate-text">${item.description}</p>
                            <div class="mt-auto">
                                <p class="mb-1"><strong>Price:</strong> Rs. <fmt:formatNumber value="${item.price}" type="number" /></p>
                                <p><strong>Stock:</strong> ${item.quantity}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <c:if test="${empty items}">
                <div class="text-center text-muted">No items found.</div>
            </c:if>
        </div>
    </div>
</main>

<!-- Reusable Footer -->
<%@ include file="/components/footer.jsp" %>

<!-- Simple JS Filter -->
<script src="${pageContext.request.contextPath}/assets/js/itemFilter.js"></script>
</body>
</html>