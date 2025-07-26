<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Pahana Edu | Help</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<!-- Reusable Components -->
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
    <div class="container mt-2">
    <h2 class="mb-3"><i class="bi bi-person-lines-fill me-2 text-primary"></i>Staff Help Guide</h2>
    <p class="text-muted">This page provides a step-by-step guide on how to use the Pahana Edu system as a staff member.</p>

    <div class="accordion" id="helpAccordion">

        <!-- Customer Management -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingCustomer">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseCustomer" aria-expanded="true" aria-controls="collapseCustomer">
                    👤 Managing Customers
                </button>
            </h2>
            <div id="collapseCustomer" class="accordion-collapse collapse show" aria-labelledby="headingCustomer" data-bs-parent="#helpAccordion">
                <div class="accordion-body">
                    <ul>
                        <li><strong>Add Customer:</strong> Go to "Add Customer" > "Add New". Fill in customer details and save.</li>
                        <li><strong>Manage Customers:</strong> Next, Go to "Manage Customers Tab"</li>
                        <li><strong>Edit:</strong> Click the "Edit" button next to a customer to update their info.</li>
                        <li><strong>Delete:</strong> Use the "Delete" button to remove a customer after confirmation.</li>
                        <li><strong>Search:</strong> Use the search bar to find a customer by name, email, or account number.</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Item Management -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingItem">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseItem" aria-expanded="false" aria-controls="collapseItem">
                    📦 Viewing Items
                </button>
            </h2>
            <div id="collapseItem" class="accordion-collapse collapse" aria-labelledby="headingItem" data-bs-parent="#helpAccordion">
                <div class="accordion-body">
                    <ul>
                        <li><strong>Browse Items:</strong> Navigate to "View Items" to browse the item catalog.</li>
                        <li><strong>Search Items:</strong> Use the search bar to find items by name or category.</li>
                        <li><strong>View Details:</strong> Each item card displays stock, price, and description.</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Billing -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingBilling">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseBilling" aria-expanded="false" aria-controls="collapseBilling">
                    🧾 Billing
                </button>
            </h2>
            <div id="collapseBilling" class="accordion-collapse collapse" aria-labelledby="headingBilling" data-bs-parent="#helpAccordion">
                <div class="accordion-body">
                    <ul>
                        <li><strong>Create Bill:</strong> Go to "Generate Bill", select a customer and add items.</li>
                        <li><strong>Search Items:</strong> Use the item filter box to quickly find items.</li>
                        <li><strong>Save Bill:</strong> Click "Generate Bill" to finalize and save.</li>
                        <li><strong>View Bills:</strong> Visit "Bill History" to view previous bills and details.</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Logout -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingExit">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExit" aria-expanded="false" aria-controls="collapseExit">
                    🚪 Logging Out
                </button>
            </h2>
            <div id="collapseExit" class="accordion-collapse collapse" aria-labelledby="headingExit" data-bs-parent="#helpAccordion">
                <div class="accordion-body">
                    <p>Click on the <strong>Logout</strong> link from the sidebar to safely log out of the system and end your session.</p>
                </div>
            </div>
        </div>

        <!-- Contact -->
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingSupport">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSupport" aria-expanded="false" aria-controls="collapseSupport">
                    📞 Support
                </button>
            </h2>
            <div id="collapseSupport" class="accordion-collapse collapse" aria-labelledby="headingSupport" data-bs-parent="#helpAccordion">
                <div class="accordion-body">
                    <p>If you need further assistance, please contact your system administrator or send an email to <strong>support@pahanaedu.lk</strong>.</p>
                    <p>
                        For system bugs, feature enhancements, or any technical inquiries related to this application,
                        please reach out to the developer:
                    </p>
                    <p>
                        <strong>Ishan Madhusha</strong><br />
                        GitHub:
                        <a href="https://github.com/PAIshanMadusha" target="_blank">
                            github.com/PAIshanMadusha
                        </a>
                    </p>
                </div>
            </div>
        </div>

    </div>
    </div>
</main>

<!-- Reusable Footer -->
<%@ include file="/components/footer.jsp" %>

<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>