package com.example.pahanaeduwebapp.servlet.staff.billing;

import com.example.pahanaeduwebapp.dao.BillDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/staff/billing/delete")
public class DeleteBillServlet extends HttpServlet {

    private BillDAO billDAO;

    @Override
    public void init() {
        billDAO = new BillDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String billId = request.getParameter("billId");

        if (billId != null && !billId.isEmpty()) {
            billDAO.deleteBillById(billId);
            request.getSession().setAttribute("success", "Bill deleted successfully.");
        }

        response.sendRedirect(request.getContextPath() + "/staff/billing/history");
    }
}