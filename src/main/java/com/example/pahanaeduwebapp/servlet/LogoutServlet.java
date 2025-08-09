package com.example.pahanaeduwebapp.servlet;

import com.example.pahanaeduwebapp.dao.AuditLogDAO;
import com.example.pahanaeduwebapp.model.AuditLog;
import com.example.pahanaeduwebapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles logout functionality for admin/staff.
 * Invalidates session and redirects to login page.
 * Also logs the logout action in audit logs.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Don't create a new session
        if (session != null) {
            // Get the logged-in user before invalidating the session
            User user = (User) session.getAttribute("user");
            if (user != null) {
                // Prepare audit log entry
                AuditLog log = new AuditLog();
                log.setUserEmail(user.getEmail());
                log.setAction("Logout");
                log.setDetails("User logged out successfully.");
                log.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                // Save log entry to DB
                AuditLogDAO auditDAO = new AuditLogDAO();
                auditDAO.logAction(log);
            }
            // Invalidate the session
            session.invalidate();
        }
        // Redirect to login page or homepage
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}