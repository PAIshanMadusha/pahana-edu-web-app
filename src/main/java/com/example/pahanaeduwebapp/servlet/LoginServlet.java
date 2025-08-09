package com.example.pahanaeduwebapp.servlet;

import com.example.pahanaeduwebapp.dao.AuditLogDAO;
import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.AuditLog;
import com.example.pahanaeduwebapp.model.User;
import com.example.pahanaeduwebapp.repository.AuditLogRepository;
import com.example.pahanaeduwebapp.util.DatabaseInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles login request and session setup.
 * Polymorphism: Uses User reference to handle both Admin and Staff objects.
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        safeExecute(request, response, () -> {
            DatabaseInitializer.ensureAdminExists();

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userDAO.validateLogin(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                AuditLog log = new AuditLog();
                log.setUserEmail(user.getEmail());
                log.setAction("Login");
                log.setDetails("User logged in");
                log.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                AuditLogRepository auditDAO = new AuditLogDAO();
                auditDAO.logAction(log);

                String role = user.getRole();

                if ("admin".equalsIgnoreCase(role)) {
                    response.sendRedirect("admin/dashboard.jsp");
                } else if ("staff".equalsIgnoreCase(role)) {
                    response.sendRedirect("staff/dashboard.jsp");
                } else {
                    request.setAttribute("error", "Unauthorized role.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid email or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        });
    }
}