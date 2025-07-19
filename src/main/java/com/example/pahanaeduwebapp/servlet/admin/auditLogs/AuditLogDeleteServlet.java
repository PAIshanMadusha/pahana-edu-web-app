package com.example.pahanaeduwebapp.servlet.admin.auditLogs;

import com.example.pahanaeduwebapp.dao.AuditLogDAO;
import com.example.pahanaeduwebapp.repository.AuditLogRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/auditLogs/delete")
public class AuditLogDeleteServlet extends HttpServlet {

    final private AuditLogRepository auditLogRepository = new AuditLogDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String logId = req.getParameter("logId");

        if (logId != null && !logId.isEmpty()) {
            auditLogRepository.deleteLogById(logId);
            req.getSession().setAttribute("successMessage", "Log deleted successfully.");
        } else {
            req.getSession().setAttribute("errorMessage", "Invalid log ID.");
        }

        resp.sendRedirect(req.getContextPath() + "/admin/auditLogs");
    }
}