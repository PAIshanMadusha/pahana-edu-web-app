package com.example.pahanaeduwebapp.servlet.staff.customer;

import com.example.pahanaeduwebapp.dao.CustomerDAO;
import com.example.pahanaeduwebapp.model.Customer;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/staff/customers/edit")
public class EditCustomerServlet extends BaseServlet {
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

            Customer customer = customerDAO.getCustomerByAccountNumber(accountNumber);

            if (customer == null) {
                response.sendRedirect(request.getContextPath() + "/staff/customers?error=Customer+not+found");
                return;
            }

            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/staff/customers/edit.jsp").forward(request, response);
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            String accountNumber = request.getParameter("accountNumber");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            Customer existing = customerDAO.getCustomerByAccountNumber(accountNumber);
            if (existing == null) {
                response.sendRedirect(request.getContextPath() + "/staff/customers?error=Customer+not+found");
                return;
            }

            String registeredDate = existing.getRegisteredDate();

            Customer updatedCustomer = new Customer(accountNumber, fullName, email, phone, address, registeredDate);

            customerDAO.updateCustomer(updatedCustomer);

            request.getSession().setAttribute("successMessage", "Customer updated successfully.");
            response.sendRedirect(request.getContextPath() + "/staff/customers");
        });
    }
}