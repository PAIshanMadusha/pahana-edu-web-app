package com.example.pahanaeduwebapp.servlet;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends BaseServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        safeExecute(request, response, () -> {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("resetEmail") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String email = (String) session.getAttribute("resetEmail");
            String newPassword = request.getParameter("password");

            User user = userDAO.findUserByEmail(email);
            if (user != null) {
                user.setPassword(newPassword);
                userDAO.updatePassword(email, newPassword);

                // Clean session and redirect
                session.removeAttribute("resetEmail");
                request.setAttribute("success", "Password reset successful. Please login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "User not found.");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            }
        });
    }
}