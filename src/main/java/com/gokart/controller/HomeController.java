package com.gokart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests for the main home page.
 * author Paras Adhikari (modified by Cline)
 */
@WebServlet("/home") // Only handles /home
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests. Forwards to the home page JSP.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Always show the home page for any GET request to /home/* or /home
        showHome(request, response);
    }

    /**
     * Forwards the request to the home page JSP.
     */
    private void showHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the correct home page JSP path (updated path)
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

    /**
     * Handles POST requests. Currently does nothing for the home page.
     * A POST request to /home is unlikely and not handled.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Optional: Redirect GET or send an error for POST requests to /home
        // For now, just let doGet handle it (which shows the home page)
        // or send a method not allowed error. Let's redirect to GET.
        response.sendRedirect(request.getContextPath() + "/home"); 
    }
}
