package com.example.pahanaeduwebapp.servlet.admin.user;

import com.example.pahanaeduwebapp.dao.UserDAO;
import com.example.pahanaeduwebapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/users/delete")
public class DeleteUserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        // Prevent deleting currently logged-in user (optional but recommended)
        User currentUser = (User) request.getSession().getAttribute("user");
        if (currentUser != null && currentUser.getEmail().equalsIgnoreCase(email)) {
            request.getSession().setAttribute("errorMessage", "You can't delete the account you're currently logged in with.");
        } else {
            boolean deleted = userDAO.deleteUserByEmail(email);
            if (deleted) {
                request.getSession().setAttribute("successMessage", "User deleted successfully.");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to delete user.");
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
