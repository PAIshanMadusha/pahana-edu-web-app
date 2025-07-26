package com.example.pahanaeduwebapp.servlet.admin.billing;

import com.example.pahanaeduwebapp.dao.BillDAO;
import com.example.pahanaeduwebapp.repository.BillRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle deletion of a bill by admin.
 */
@WebServlet("/admin/bills/delete")
public class AdminDeleteBillServlet extends HttpServlet {

    private BillRepository billRepository;

    @Override
    public void init() throws ServletException {
        billRepository = new BillDAO(); // Using DAO implementation
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String billId = request.getParameter("billId");

        if (billId != null && !billId.trim().isEmpty()) {
            try {
                billRepository.deleteBillById(billId);
                request.getSession().setAttribute("successMessage", "Bill deleted successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("errorMessage", "Error deleting bill.");
            }
        } else {
            request.getSession().setAttribute("errorMessage", "Invalid bill ID.");
        }

        response.sendRedirect(request.getContextPath() + "/admin/billing/bills");
    }
}