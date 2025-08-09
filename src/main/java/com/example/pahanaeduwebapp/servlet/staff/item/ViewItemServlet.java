package com.example.pahanaeduwebapp.servlet.staff.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for staff to view all available items.
 */

@WebServlet("/staff/items")
public class ViewItemServlet extends BaseServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() throws ServletException {
        itemDAO = new ItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            List<Item> itemList = itemDAO.getAllItems();
            request.setAttribute("items", itemList);
            request.getRequestDispatcher("/staff/items/view.jsp").forward(request, response);
        });
    }
}