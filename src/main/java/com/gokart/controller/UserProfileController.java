package com.gokart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userprofile")
public class UserProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            // Forward to profile update page without header
            request.getRequestDispatcher("/WEB-INF/pages/profileUpdate.jsp").forward(request, response);
        } else {
            // Show regular profile page with header
            request.getRequestDispatcher("/WEB-INF/pages/user_prof.jsp").forward(request, response);
        }
    }
}
