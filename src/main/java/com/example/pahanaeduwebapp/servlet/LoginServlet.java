package com.example.pahanaeduwebapp.servlet;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;
import com.example.pahanaeduwebapp.util.DatabaseInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Handles login form submission and session creation.
 * Verifies user credentials using UserDAO.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Ensure admin exists!
        DatabaseInitializer.ensureAdminExists();

        //Get credentials from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Validate credentials using DAO
        User user = userDAO.validateLogin(email, password);

        if (user != null) {
            //Create session & store user
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            //Redirect based on user role
            String role = user.getRole();

            if ("admin".equalsIgnoreCase(role)) {
                response.sendRedirect("admin/dashboard.jsp");
            } else if ("staff".equalsIgnoreCase(role)) {
                response.sendRedirect("staff/dashboard.jsp");
            } else {
                //Unknown role - optional fallback
                request.setAttribute("error", "Unauthorized role.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            //Login failed
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
