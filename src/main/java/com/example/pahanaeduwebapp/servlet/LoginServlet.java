package com.example.pahanaeduwebapp.servlet;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;
import com.example.pahanaeduwebapp.util.DatabaseInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Handles login request and session setup.
 * Polymorphism: Uses User reference to handle both Admin and Staff objects.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DatabaseInitializer.ensureAdminExists();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.validateLogin(email, password); // Polymorphic return

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Holds Admin or Staff

            // Use polymorphism to redirect by role
            String role = user.getRole();

            if ("admin".equalsIgnoreCase(role)) {
                response.sendRedirect("admin/dashboard.jsp");
            } else if ("staff".equalsIgnoreCase(role)) {
                response.sendRedirect("staff/dashboard.jsp");
            } else {
                request.setAttribute("error", "Unauthorized role.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}