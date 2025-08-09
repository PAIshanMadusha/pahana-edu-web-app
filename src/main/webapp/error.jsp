<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Pahana Edu | Error</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />
</head>
<body class="d-flex vh-100">

<div class="container my-auto">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="alert alert-danger text-center shadow">
                <h2 class="mb-3">Something Went Wrong!</h2>
                <p>An unexpected error occurred, Please try again later</p>

                <c:if test="${not empty exception}">
                    <div class="small text-muted mt-3 text-break">
                        <strong>Technical details:</strong><br />
                            ${exception.message}
                    </div>
                </c:if>

                <div class="mt-4">
                    Contact the Developer:
                    <a href="https://github.com/PAIshanMadusha" target="_blank" rel="noopener noreferrer" class="fw-bold text-danger text-decoration-none">
                        https://github.com/PAIshanMadusha
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>