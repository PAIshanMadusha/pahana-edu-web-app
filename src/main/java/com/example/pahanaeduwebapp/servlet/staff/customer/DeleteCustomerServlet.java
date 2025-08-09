package com.example.pahanaeduwebapp.servlet.staff.customer;

import com.example.pahanaeduwebapp.dao.CustomerDAO;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle deleting a customer by account number.
 */
@WebServlet("/staff/customers/delete")
public class DeleteCustomerServlet extends BaseServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        safeExecute(request, response, () -> {
            String accountNumber = request.getParameter("account");

            if (accountNumber == null || accountNumber.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/staff/customers?error=Invalid+account+number");
                return;
            }

            customerDAO.deleteCustomer(accountNumber);
            request.getSession().setAttribute("successMessage", "Customer deleted successfully.");
            response.sendRedirect(request.getContextPath() + "/staff/customers");
        });
    }
}
