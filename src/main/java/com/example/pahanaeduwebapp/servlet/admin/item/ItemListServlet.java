package com.example.pahanaeduwebapp.servlet.admin.item;

import com.example.pahanaeduwebapp.dao.ItemDAO;
import com.example.pahanaeduwebapp.model.Item;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to load all items and forward to the list.jsp view.
 * OOP Concepts:
 * - Abstraction: Uses ItemDAO to hide MongoDB logic
 * - Encapsulation: Accesses item fields through getters
 */
@WebServlet("/admin/items")
public class ItemListServlet extends BaseServlet {

    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            List<Item> itemList = itemDAO.getAllItems();
            request.setAttribute("itemList", itemList);
            request.getRequestDispatcher("/admin/items/list.jsp").forward(request, response);
        });
    }
}
