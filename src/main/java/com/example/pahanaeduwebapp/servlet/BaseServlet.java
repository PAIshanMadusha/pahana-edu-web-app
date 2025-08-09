package com.example.pahanaeduwebapp.servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected void safeExecute(HttpServletRequest request, HttpServletResponse response, ServletTask task)
            throws ServletException, IOException {
        try {
            task.run();
        } catch (Exception e) {
            e.printStackTrace(); // Optional: use logger
            request.setAttribute("error", "An unexpected error occurred.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @FunctionalInterface
    protected interface ServletTask {
        void run() throws ServletException, IOException;
    }
}