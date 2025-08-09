package com.example.pahanaeduwebapp.servlet.admin.user;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;
import com.example.pahanaeduwebapp.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle adding a new user from the admin panel.
 * OOP Used: Polymorphism (creates Admin or Staff using base type reference)
 */
@WebServlet("/admin/users/add")
public class AddUserServlet extends BaseServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        safeExecute(request, response, () -> {
            String email = request.getParameter("email");
            String password = request.getParameter("password"); // TODO: hash in prod
            String fullName = request.getParameter("fullName");
            String role = request.getParameter("role");
            String phone = request.getParameter("phone");

            if (email == null || password == null || role == null) {
                response.sendRedirect(request.getContextPath() + "/admin/users/add.jsp?error=Missing+fields");
                return;
            }

            User newUser = User.createByRole(role, email, password, fullName, phone);

            boolean added = userDAO.addUser(newUser);
            if (!added) {
                // duplicate
                request.getSession().setAttribute("errorMessage", "Email already exists.");
                response.sendRedirect(request.getContextPath() + "/admin/users");
                return;
            }

            request.getSession().setAttribute("successMessage", "User added successfully.");
            response.sendRedirect(request.getContextPath() + "/admin/users");
        });
    }
}
