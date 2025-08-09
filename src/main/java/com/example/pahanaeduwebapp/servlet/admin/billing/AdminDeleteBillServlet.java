package com.example.pahanaeduwebapp.servlet.admin.billing;

import com.example.pahanaeduwebapp.dao.BillDAO;
import com.example.pahanaeduwebapp.repository.BillRepository;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle deletion of a bill by admin.
 */
@WebServlet("/admin/bills/delete")
public class AdminDeleteBillServlet extends BaseServlet {

    private BillRepository billRepository;

    @Override
    public void init() throws ServletException {
        billRepository = new BillDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            String billId = request.getParameter("billId");

            if (billId != null && !billId.trim().isEmpty()) {
                billRepository.deleteBillById(billId);
                request.getSession().setAttribute("successMessage", "Bill deleted successfully.");
            } else {
                request.getSession().setAttribute("errorMessage", "Invalid bill ID.");
            }

            response.sendRedirect(request.getContextPath() + "/admin/billing/bills");
        });
    }
}
