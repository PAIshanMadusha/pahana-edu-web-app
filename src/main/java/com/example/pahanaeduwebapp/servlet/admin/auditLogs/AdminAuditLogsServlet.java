package com.example.pahanaeduwebapp.servlet.admin.auditLogs;

import com.example.pahanaeduwebapp.dao.AuditLogDAO;
import com.example.pahanaeduwebapp.model.AuditLog;
import com.example.pahanaeduwebapp.repository.AuditLogRepository;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/auditLogs")
public class AdminAuditLogsServlet extends BaseServlet {

    private AuditLogRepository auditLogRepository;

    @Override
    public void init() {
        auditLogRepository = new AuditLogDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            List<AuditLog> logs = auditLogRepository.getAllLogs();
            request.setAttribute("auditLogs", logs);
            request.getRequestDispatcher("/admin/auditLogs.jsp").forward(request, response);
        });
    }
}