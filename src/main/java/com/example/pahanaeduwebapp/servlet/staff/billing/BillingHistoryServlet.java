package com.example.pahanaeduwebapp.servlet.staff.billing;

import com.example.pahanaeduwebapp.dao.BillDAO;
import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Displays all previously generated bills (Billing History).
 */
@WebServlet("/staff/billing/history")
public class BillingHistoryServlet extends BaseServlet {

    private BillDAO billDAO;

    @Override
    public void init() {
        billDAO = new BillDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            List<Bill> bills = billDAO.getAllBills();
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("/staff/billing/history.jsp").forward(request, response);
        });
    }
}
