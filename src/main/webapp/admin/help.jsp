<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <title>Help - Admin</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap-icons/bootstrap-icons.css">
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">

<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
  <div class="container mt-4">
    <h2 class="mb-3"><i class="bi bi-shield-lock-fill me-2 text-primary"></i>Admin Help Guide</h2>
    <p class="text-muted">This guide helps administrators manage and control the Pahana Edu System effectively.</p>

    <div class="accordion" id="adminHelpAccordion">

      <!-- User Management -->
      <div class="accordion-item">
        <h2 class="accordion-header" id="headingUser">
          <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseUser" aria-expanded="true" aria-controls="collapseUser">
            🧑‍💼 User Management
          </button>
        </h2>
        <div id="collapseUser" class="accordion-collapse collapse show" aria-labelledby="headingUser" data-bs-parent="#adminHelpAccordion">
          <div class="accordion-body">
            <ul>
              <li><strong>Add User:</strong> Navigate to "Manage Users" > "Add New User", fill user details including role, then save.</li>
              <li><strong>Edit User:</strong> Use the "Edit" button next to a user to modify their information or role.</li>
              <li><strong>Delete User:</strong> Use the "Delete" button carefully to remove user accounts.</li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Item Management -->
      <div class="accordion-item">
        <h2 class="accordion-header" id="headingItem">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseItem" aria-expanded="false" aria-controls="collapseItem">
            📦 Item Management
          </button>
        </h2>
        <div id="collapseItem" class="accordion-collapse collapse" aria-labelledby="headingItem" data-bs-parent="#adminHelpAccordion">
          <div class="accordion-body">
            <ul>
              <li><strong>Add Item:</strong> Click on "Add New Item", enter details such as name, price, and quantity, then submit.</li>
              <li><strong>Edit Item:</strong> Modify item info through the "Edit" option in the item list.</li>
              <li><strong>Delete Item:</strong> Remove items when necessary using the "Delete" button.</li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Billing Oversight -->
      <div class="accordion-item">
        <h2 class="accordion-header" id="headingBilling">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseBilling" aria-expanded="false" aria-controls="collapseBilling">
            🧾 Billing Oversight
          </button>
        </h2>
        <div id="collapseBilling" class="accordion-collapse collapse" aria-labelledby="headingBilling" data-bs-parent="#adminHelpAccordion">
          <div class="accordion-body">
            <ul>
              <li><strong>View Bills:</strong> Access all bills under the "View Bills" section to audit transactions.</li>
              <li><strong>Staff Billing:</strong> Admin has the ability to view, print, and delete bills created by staff members.</li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Reports -->
      <div class="accordion-item">
        <h2 class="accordion-header" id="headingReports">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseReports" aria-expanded="false" aria-controls="collapseReports">
            📊 Reports & Analytics
          </button>
        </h2>
        <div id="collapseReports" class="accordion-collapse collapse" aria-labelledby="headingReports" data-bs-parent="#adminHelpAccordion">
          <div class="accordion-body">
            <ul>
              <li><strong>Monthly Revenue:</strong> Displays the total revenue generated in the current month.</li>
              <li><strong>Bill Counts:</strong> Visualizes the total number of bills issued by each staff member.</li>
              <li><strong>Recent Activity:</strong> Shows bills created over the last 7 days for quick insights.</li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Logs -->
      <div class="accordion-item">
        <h2 class="accordion-header" id="headingLogs">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseLogs" aria-expanded="false" aria-controls="collapseLogs">
            📝 Audit Logs
          </button>
        </h2>
        <div id="collapseLogs" class="accordion-collapse collapse" aria-labelledby="headingLogs" data-bs-parent="#adminHelpAccordion">
          <div class="accordion-body">
            <ul>
              <li><strong>System Logs:</strong> Check system logs for critical actions (e.g. Getting logs: Staff and admin log-in and log-out times).</li>
              <li><strong>Log Cleanup:</strong> Remove old logs using the "remove" button if necessary.</li>
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
        <div id="collapseExit" class="accordion-collapse collapse" aria-labelledby="headingExit" data-bs-parent="#adminHelpAccordion">
          <div class="accordion-body">
            <p>Click <strong>Logout</strong> from the sidebar to safely end your session.</p>
          </div>
        </div>
      </div>

    </div>
  </div>
</main>

<%@ include file="/components/footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>