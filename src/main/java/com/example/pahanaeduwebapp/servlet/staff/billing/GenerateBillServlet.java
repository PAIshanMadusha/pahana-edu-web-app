package com.example.pahanaeduwebapp.servlet.staff.billing;

import com.example.pahanaeduwebapp.dao.BillDAO;
import com.example.pahanaeduwebapp.dao.CustomerDAO;
import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Bill;
import com.example.pahanaeduwebapp.model.BillItem;
import com.example.pahanaeduwebapp.model.Customer;
import com.example.pahanaeduwebapp.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Handles generating a bill from selected items.
 * OOP: Uses DAO abstraction to persist data.
 */
@WebServlet("/staff/billing/generate")
public class GenerateBillServlet extends HttpServlet {
    private BillDAO billDAO;
    private ItemDAO itemDAO;

    @Override
    public void init() {
        billDAO = new BillDAO();
        itemDAO = new ItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get all items from DB to display in the form
        List<Item> itemList = itemDAO.getAllItems();

        // Set items to request scope
        request.setAttribute("itemList", itemList);

        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customerList = customerDAO.getAllCustomers();
        request.setAttribute("customerList", customerList);

        // Forward to generate.jsp
        request.getRequestDispatcher("/staff/billing/generate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerAccountNumber = request.getParameter("accountNumber");
        String[] itemIds = request.getParameterValues("itemId");
        String[] quantities = request.getParameterValues("quantity");

        List<BillItem> billItems = new ArrayList<>();

        // Build BillItem list
        for (int i = 0; i < itemIds.length; i++) {
            String itemId = itemIds[i];
            int qty = Integer.parseInt(quantities[i]);

            Item dbItem = itemDAO.getItemById(itemId);
            if (dbItem != null && qty > 0) {
                BillItem item = new BillItem(
                        dbItem.getItemId(),
                        dbItem.getName(),
                        dbItem.getPrice(),
                        qty
                );
                billItems.add(item);
            }
        }

        // Create Bill
        String billId = UUID.randomUUID().toString().substring(0, 8);
        String createdAt = LocalDateTime.now().toString();
        Bill bill = new Bill(billId, customerAccountNumber, billItems, 0.0, createdAt);
        bill.setTotalAmount(bill.calculateTotal());

        // Save to DB
        billDAO.saveBill(bill);

        // Fetch customer email
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerByAccountNumber(customerAccountNumber);
        String customerEmail = (customer != null) ? customer.getEmail() : "N/A";

        // Redirect to print page (optional)
        request.setAttribute("bill", bill);
        request.setAttribute("customerEmail", customerEmail);
        request.getRequestDispatcher("/staff/billing/print.jsp").forward(request, response);
    }
}