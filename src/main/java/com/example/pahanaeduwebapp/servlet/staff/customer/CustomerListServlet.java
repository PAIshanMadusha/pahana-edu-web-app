package com.example.pahanaeduwebapp.servlet.staff.customer;

import com.example.pahanaeduwebapp.dao.CustomerDAO;
import com.example.pahanaeduwebapp.model.Customer;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/* * Servlet for staff to view all customers.
 * Loads all customers and forwards to list.jsp for staff view.
 */
@WebServlet("/staff/customers")
public class CustomerListServlet extends BaseServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        safeExecute(request, response, () -> {
            String search = request.getParameter("search");

            List<Customer> customerList;
            if (search != null && !search.trim().isEmpty()) {
                customerList = customerDAO.searchCustomers(search.trim());
                request.setAttribute("searchQuery", search.trim());
            } else {
                customerList = customerDAO.getAllCustomers();
            }

            request.setAttribute("customerList", customerList);
            request.getRequestDispatcher("/staff/customers/list.jsp").forward(request, response);
        });
    }
}