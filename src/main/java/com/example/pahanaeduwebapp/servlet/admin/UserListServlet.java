package com.example.pahanaeduwebapp.servlet.admin;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet to list all users for admin panel.
 * Loads data from MongoDB and forwards to list.jsp.
 */
@WebServlet("/admin/users")
public class UserListServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get list of users from database
        List<User> userList = userDAO.getAllUsers();

        // Set data to request scope
        request.setAttribute("userList", userList);

        // Forward to JSP
        request.getRequestDispatcher("/admin/users/list.jsp").forward(request, response);

    }
}
