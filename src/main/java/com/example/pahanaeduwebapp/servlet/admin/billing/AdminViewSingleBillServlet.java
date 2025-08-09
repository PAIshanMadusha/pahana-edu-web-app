package com.example.pahanaeduwebapp.servlet.admin.billing;

import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.repository.BillRepository;
import com.example.pahanaeduwebapp.dao.BillDAO;
import com.example.pahanaeduwebapp.dao.CustomerDAO;
import com.example.pahanaeduwebapp.model.Customer;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/billing/view")
public class AdminViewSingleBillServlet extends BaseServlet {

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

            if (billId == null || billId.isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Invalid bill ID.");
                response.sendRedirect(request.getContextPath() + "/admin/billing/bills");
                return;
            }

            Bill bill = billRepository.getBillById(billId);
            if (bill == null) {
                request.getSession().setAttribute("errorMessage", "Bill not found.");
                response.sendRedirect(request.getContextPath() + "/admin/billing/bills");
                return;
            }

            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getCustomerByAccountNumber(bill.getCustomerAccountNumber());
            String customerEmail = (customer != null) ? customer.getEmail() : "N/A";

            request.setAttribute("bill", bill);
            request.setAttribute("customerEmail", customerEmail);
            request.getRequestDispatcher("/admin/billing/view.jsp").forward(request, response);
        });
    }
}
