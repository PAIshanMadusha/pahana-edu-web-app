<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>Reset Password</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <div class="col-md-6 offset-md-3">
    <div class="card p-4">
      <h4 class="text-center mb-3">Reset Your Password</h4>

      <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
      </c:if>

      <form action="${pageContext.request.contextPath}/reset-password" method="post">
        <div class="mb-3">
          <label>New Password</label>
          <input type="password" name="password" class="form-control" required>
        </div>
        <div class="d-grid mb-2">
          <button type="submit" class="btn btn-primary">Reset Password</button>
        </div>
      </form>
      <form action="${pageContext.request.contextPath}/forgot-password.jsp" method="get">
        <div class="d-grid">
          <button type="submit" class="btn btn-secondary">Back</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>