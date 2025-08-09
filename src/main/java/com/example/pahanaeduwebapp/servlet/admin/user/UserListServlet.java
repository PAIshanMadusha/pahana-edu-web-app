package com.example.pahanaeduwebapp.servlet.admin.user;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * Servlet to list all users for admin panel.
 * Loads data from MongoDB and forwards to list.jsp.
 */
@WebServlet("/admin/users")
public class UserListServlet extends BaseServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            List<User> userList = userDAO.getAllUsers();
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("/admin/users/list.jsp").forward(request, response);
        });
    }
}
