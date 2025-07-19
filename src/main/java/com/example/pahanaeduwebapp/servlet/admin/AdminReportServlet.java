package com.example.pahanaeduwebapp.servlet.admin;

import com.example.pahanaeduwebapp.dao.ReportDAO;
import com.example.pahanaeduwebapp.model.Bill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * AdminReportServlet handles report viewing for admin users.
 * Loads:
 * - Bills in last 7 days
 * - Total bills per user
 * - This month's total revenue
 */
@WebServlet("/admin/reports")
public class AdminReportServlet extends HttpServlet {

    private final ReportDAO reportDAO = new ReportDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Date range: last 7 days
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDate = weekAgo.format(formatter);
        String toDate = today.format(formatter);

        List<Bill> recentBills = reportDAO.getBillsByDateRange(fromDate, toDate);
        request.setAttribute("recentBills", recentBills);

        //Total bills by user
        Map<String, Integer> totalBillsByUser = reportDAO.getTotalBillsByUser();
        request.setAttribute("billCountByUser", totalBillsByUser);

        //Revenue this month
        String yearMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        double monthlyRevenue = reportDAO.getTotalRevenueByMonth(yearMonth);
        request.setAttribute("monthlyRevenue", monthlyRevenue);

        //Daily progress data (past 7 days)
        Map<String, Integer> billsByDate = reportDAO.getBillsGroupedByDate(7);
        request.setAttribute("billsByDate", billsByDate);

        //Forward to JSP
        request.getRequestDispatcher("/admin/reports.jsp").forward(request, response);
    }
}