<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>Pahana Edu | Reports</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        @media (max-width: 768px) {
            .chart-wrapper {
                flex-direction: column;
            }
        }
    </style>
</head>
<body style="margin-left: 70px; min-height: 100vh;" class="d-flex flex-column">

<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
        <h2 class="mb-4"><i class="bi bi-shield-lock-fill me-2 text-primary"></i>View Reports</h2>

        <!-- Monthly Revenue Card -->
        <div class="mb-5">
            <div class="card text-white bg-success shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Monthly Revenue</h5>
                    <p class="display-4">
                        <fmt:formatNumber value="${monthlyRevenue}" type="currency" currencySymbol="LKR " />
                    </p>
                    <small class="text-white-50">Revenue for the current month</small>
                </div>
            </div>
        </div>

        <!-- Charts Section -->
        <div class="row mb-5">
            <!-- Total Bills by User -->
            <div class="col-lg-6 col-md-12 mb-4">
                <h4>Total Bills by User</h4>
                <canvas id="billsByUserChart"></canvas>
            </div>

            <!-- Daily Progress -->
            <div class="col-lg-6 col-md-12">
                <h4>Daily Bills Progress (Last 7 Days)</h4>
                <canvas id="dailyProgressChart"></canvas>
            </div>
        </div>

        <!-- Recent Bills Section -->
        <h4 class="mb-3">Recent Bills (Last 7 Days)</h4>
        <div class="row g-3 mb-5">
            <c:choose>
                <c:when test="${not empty recentBills}">
                    <c:forEach var="bill" items="${recentBills}">
                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Bill ID: ${bill.billId}</h5>
                                    <p class="card-text">
                                        <strong>Customer:</strong> ${bill.customerAccountNumber} <br />
                                        <strong>Date:</strong> ${bill.createdAt} <br />
                                        <strong>Total:</strong> LKR ${bill.totalAmount}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-muted">No recent bills found.</p>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</main>

<%@ include file="/components/footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>

<script>
    // Total Bills by User Chart
    const billData = {
        labels: [
            <c:forEach var="entry" items="${billCountByUser.keySet()}" varStatus="status">
            '${entry}'<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ],
        datasets: [{
            label: 'Total Bills',
            data: [
                <c:forEach var="entry" items="${billCountByUser.values()}" varStatus="status">
                ${entry}<c:if test="${!status.last}">,</c:if>
                </c:forEach>
            ],
            backgroundColor: 'rgba(54, 162, 235, 0.6)'
        }]
    };

    const config = {
        type: 'bar',
        data: billData,
        options: {
            scales: { y: { beginAtZero: true, stepSize: 1 } },
            plugins: { legend: { display: false } }
        }
    };

    new Chart(document.getElementById('billsByUserChart').getContext('2d'), config);

    // Daily Progress Chart
    const dailyProgressData = {
        labels: [
            <c:forEach var="entry" items="${billsByDate.keySet()}" varStatus="status">
            '${entry}'<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ],
        datasets: [{
            label: 'Bills Generated',
            data: [
                <c:forEach var="entry" items="${billsByDate.values()}" varStatus="status">
                ${entry}<c:if test="${!status.last}">,</c:if>
                </c:forEach>
            ],
            backgroundColor: 'rgba(255, 99, 132, 0.6)'
        }]
    };

    const dailyProgressConfig = {
        type: 'bar',
        data: dailyProgressData,
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: { stepSize: 1 }
                }
            },
            plugins: {
                legend: { display: false }
            }
        }
    };

    new Chart(document.getElementById('dailyProgressChart').getContext('2d'), dailyProgressConfig);
</script>

</body>
</html>