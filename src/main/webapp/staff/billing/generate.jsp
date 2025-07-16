<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <title>Generate Bill</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.min.css">

  <!-- CSS Inline -->
  <style>
    .select2-container--default .select2-search--dropdown .select2-search__field {
      border-radius: 0.375rem;
      padding: 0.375rem 0.75rem;
      border: 1px solid #ced4da;
    }

    .sticky-generate {
      position: sticky;
      bottom: 0;
      right: 0;
      left: 250px;
      z-index: 1030;
      background-color: #fff;
      border-top: 1px solid #dee2e6;
      padding: 1rem;
      text-align: right;
    }
  </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh; margin-left: 70px;">
<%@ include file="/components/header.jsp" %>
<%@ include file="/components/sidebar.jsp" %>

<main class="flex-grow-1 p-4">
  <div class="container mt-4">
    <h2 class="mb-4">Generate New Bill</h2>

    <form action="${pageContext.request.contextPath}/staff/billing/generate" method="post">

      <!-- Customer Selection -->
      <div class="row align-items-end mb-3">
        <div class="col-md-8">
          <label for="accountNumber" class="form-label">Search & Select Customer</label>
          <select name="accountNumber" id="accountNumber" class="form-select" required>
            <option value="" disabled selected>Select customer by account number or email</option>
            <c:forEach var="customer" items="${customerList}">
              <option value="${customer.accountNumber}">
                  ACC# ${customer.accountNumber} : ${customer.email}
              </option>
            </c:forEach>
          </select>
        </div>

        <!-- Button aligned to the right -->
        <div class="col-md-4 d-flex justify-content-end">
          <a href="${pageContext.request.contextPath}/staff/customers/add" class="btn btn-success mt-4">
            <i class="bi bi-person-plus"></i> Add New Customer
          </a>
        </div>
      </div>

      <!-- Item Filter -->
      <div class="mb-3">
        <input type="text" id="itemSearch" class="form-control" placeholder="Search items by name...">
      </div>

      <!-- Item Table -->
      <div class="table-responsive mb-5">
        <table class="table table-bordered table-striped" id="itemTable">
          <thead class="table-dark">
          <tr>
            <th>Item Name</th>
            <th>Price (Rs.)</th>
            <th>Quantity</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="item" items="${itemList}">
            <tr>
              <td class="item-name">${item.name}</td>
              <td>${item.price}</td>
              <td>
                <input type="hidden" name="itemId" value="${item.itemId}" />
                <input type="number" name="quantity" class="form-control" value="0" min="0" />
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

      <!-- Sticky Generate Button -->
      <div class="sticky-generate">
        <button type="submit" class="btn btn-primary">Generate Bill</button>
      </div>

    </form>
  </div>
</main>

<%@ include file="/components/footer.jsp" %>
<!-- jQuery FIRST -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- custom logic (depends on jQuery & Select2) -->
<script src="${pageContext.request.contextPath}/assets/js/billing.js"></script>
</body>
</html>