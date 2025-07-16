package com.example.pahanaeduwebapp.servlet.staff.billing;

import com.example.pahanaeduwebapp.dao.BillDAO;
import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.dao.CustomerDAO;
import com.example.pahanaeduwebapp.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Loads a previously generated bill for viewing.
 */
@WebServlet("/staff/billing/view")
public class ViewBillServlet extends HttpServlet {

    private BillDAO billDAO;
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        billDAO = new BillDAO();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String billId = request.getParameter("billId");

        if (billId == null || billId.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/staff/billing/history");
            return;
        }

        Bill bill = billDAO.getBillById(billId);
        if (bill == null) {
            request.setAttribute("error", "Bill not found.");
            request.getRequestDispatcher("/staff/billing/history.jsp").forward(request, response);
            return;
        }

        //Fetch customer email using customerAccountNumber from bill
        Customer customer = customerDAO.getCustomerByAccountNumber(bill.getCustomerAccountNumber());
        String customerEmail = (customer != null) ? customer.getEmail() : "N/A";

        request.setAttribute("bill", bill);
        request.setAttribute("customerEmail", customerEmail);
        request.getRequestDispatcher("/staff/billing/print.jsp").forward(request, response);
    }
}