package com.example.pahanaeduwebapp.servlet.admin.user;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet to handle adding a new user from the admin panel.
 */
@WebServlet("/admin/users/add")
public class AddUserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // In production: hash this
        String fullName = request.getParameter("fullName");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");

        // Basic null check
        if (email == null || password == null || role == null) {
            response.sendRedirect(request.getContextPath() + "/admin/users/add.jsp?error=Missing+fields");
            return;
        }

        // Create user object
        User newUser = new User(email, password, role, fullName, phone);

        // Save to DB
        userDAO.addUser(newUser);

        request.getSession().setAttribute("successMessage", "User added successfully.");

        // Redirect back to user list
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
