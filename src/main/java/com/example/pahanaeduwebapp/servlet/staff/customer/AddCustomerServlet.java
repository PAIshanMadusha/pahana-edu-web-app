package com.example.pahanaeduwebapp.servlet.staff.customer;

import com.example.pahanaeduwebapp.dao.CustomerDAO;
import com.example.pahanaeduwebapp.model.Customer;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@WebServlet("/staff/customers/add")
public class AddCustomerServlet extends BaseServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            request.getRequestDispatcher("/staff/customers/add.jsp").forward(request, response);
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            // Collect form data
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            // Generate unique account number using UUID
            String accountNumber = UUID.randomUUID().toString().substring(0, 8);

            // Get today's date for registration
            String registeredDate = LocalDate.now().toString();

            // Create customer object
            Customer customer = new Customer(accountNumber, fullName, email, phone, address, registeredDate);

            // Save to DB
            customerDAO.addCustomer(customer);

            // Redirect with success message
            request.getSession().setAttribute("successMessage", "Customer added successfully.");
            response.sendRedirect(request.getContextPath() + "/staff/customers");
        });
    }
}