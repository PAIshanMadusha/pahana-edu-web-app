package com.example.pahanaeduwebapp.servlet.admin.auditLogs;

import com.example.pahanaeduwebapp.dao.AuditLogDAO;
import com.example.pahanaeduwebapp.model.AuditLog;
import com.example.pahanaeduwebapp.repository.AuditLogRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/auditLogs")
public class AdminAuditLogsServlet extends HttpServlet {

    private AuditLogRepository auditLogRepository;

    @Override
    public void init() throws ServletException {
        auditLogRepository = new AuditLogDAO();  // Concrete DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AuditLog> logs = auditLogRepository.getAllLogs();
        request.setAttribute("auditLogs", logs);
        request.getRequestDispatcher("/admin/auditLogs.jsp").forward(request, response);
    }
}