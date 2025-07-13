package com.example.pahanaeduwebapp.servlet.admin;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Handles loading and updating a user for admin.
 */
@WebServlet("/admin/users/edit")
public class EditUserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        }

        User user = userDAO.findUserByEmail(email);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        }

        request.setAttribute("userToEdit", user);
        request.getRequestDispatcher("/admin/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");

        User updatedUser = new User(email, null, role, fullName, phone);
        userDAO.updateUser(updatedUser);

        request.getSession().setAttribute("successMessage", "User updated successfully.");
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}