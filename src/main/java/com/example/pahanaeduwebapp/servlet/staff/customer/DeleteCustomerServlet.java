package com.example.pahanaeduwebapp.servlet.staff.customer;

import com.example.pahanaeduwebapp.dao.CustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle deleting a customer by account number.
 */
@WebServlet("/staff/customers/delete")
public class DeleteCustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the account number from the request
        String accountNumber = request.getParameter("account");

        if (accountNumber == null || accountNumber.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/staff/customers?error=Invalid+account+number");
            return;
        }

        // Delete the customer using DAO
        customerDAO.deleteCustomer(accountNumber);

        // Set a success message and redirect to list
        request.getSession().setAttribute("successMessage", "Customer deleted successfully.");
        response.sendRedirect(request.getContextPath() + "/staff/customers");
    }
}
