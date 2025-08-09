package com.example.pahanaeduwebapp.servlet.admin;

import com.example.pahanaeduwebapp.dao.ReportDAO;
import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * AdminReportServlet handles report viewing for admin users.
 * Bills in last 7 days
 * Total bills per user
 * This month's total revenue
 */
@WebServlet("/admin/reports")
public class AdminReportServlet extends BaseServlet {

    private final ReportDAO reportDAO = new ReportDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        safeExecute(request, response, () -> {
            LocalDate today = LocalDate.now();
            LocalDate weekAgo = today.minusDays(6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fromDate = weekAgo.format(formatter);
            String toDate = today.format(formatter);

            List<Bill> recentBills = reportDAO.getBillsByDateRange(fromDate, toDate);
            request.setAttribute("recentBills", recentBills);

            Map<String, Integer> totalBillsByUser = reportDAO.getTotalBillsByUser();
            request.setAttribute("billCountByUser", totalBillsByUser);

            String yearMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            double monthlyRevenue = reportDAO.getTotalRevenueByMonth(yearMonth);
            request.setAttribute("monthlyRevenue", monthlyRevenue);

            Map<String, Integer> billsByDate = reportDAO.getBillsGroupedByDate(7);
            request.setAttribute("billsByDate", billsByDate);

            request.getRequestDispatcher("/admin/reports.jsp").forward(request, response);
        });
    }
}