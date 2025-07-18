package com.example.pahanaeduwebapp.servlet.admin.billing;

import com.example.pahanaeduwebapp.dao.BillDAO;
import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.repository.BillRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to handle admin viewing all bill history.
 */
@WebServlet("/admin/billing/bills")
public class AdminViewBillsServlet extends HttpServlet {

    private BillRepository billRepository;

    @Override
    public void init() throws ServletException {
        billRepository = new BillDAO(); // Concrete implementation
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Bill> bills = billRepository.getAllBills();
        request.setAttribute("bills", bills);
        request.getRequestDispatcher("/admin/billing/bills.jsp").forward(request, response);
    }
}