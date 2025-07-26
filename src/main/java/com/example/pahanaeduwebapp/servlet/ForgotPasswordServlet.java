package com.example.pahanaeduwebapp.servlet;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User user = userDAO.findUserByEmail(email);

        if (user != null && user.getPhone().equals(phone)) {
            // Store verified email in session
            HttpSession session = request.getSession();
            session.setAttribute("resetEmail", email);
            response.sendRedirect("reset-password.jsp");
        } else {
            request.setAttribute("error", "Email or mobile number is incorrect.");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
        }
    }
}
