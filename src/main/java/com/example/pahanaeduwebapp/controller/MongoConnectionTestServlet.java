package com.example.pahanaeduwebapp.controller;

import com.example.pahanaeduwebapp.util.MongoDBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test-mongo") //http://localhost:8081/pahana_edu_web_app_war_exploded/test-mongo
public class MongoConnectionTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            MongoDBConnection.getDatabase().listCollectionNames().first();
            out.println("<h3 style='color:green;'>✅ MongoDB Connected Successfully!</h3>");
            System.out.println("✅ MongoDB Connected Successfully (from servlet)");
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>❌ Connection failed: " + e.getMessage() + "</h3>");
            System.err.println("❌ MongoDB Connection Failed: " + e.getMessage());
        }
    }
}
