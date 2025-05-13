package com.gokart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.gokart.model.UserModel;
import com.gokart.service.LoginService;
import com.gokart.util.CookieUtil;
import com.gokart.util.SessionUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = null;

        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            errorMessage = "Username and password are required.";
        } else {
            try {
                // Check for admin first
                if ("admin".equals(username) && "admin123".equals(password)) {
                    // Create an admin UserModel instance
                    UserModel adminUser = new UserModel();
                    adminUser.setUsername("admin");
                    adminUser.setRole("admin");  // Set the role to "admin"
                    adminUser.setFirstName("Admin"); //Set First Name
                    adminUser.setLastName("User");  //Set Last Name

                    HttpSession session = request.getSession();
                    session.setAttribute("user", adminUser);
                    SessionUtil.createUserSession(request, adminUser); // Use createUserSession
                    CookieUtil.createCookie(response, "role", "admin", 30 * 60);
                    response.sendRedirect(request.getContextPath() + "/usercontrol");
                    return;
                }

                // If not admin, check against the database
                UserModel user = loginService.validateUser(username, password);

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    SessionUtil.createUserSession(request, user); // Use createUserSession
                    CookieUtil.createCookie(response, "role", "customer", 30 * 60);
                    String redirectUrl = request.getParameter("redirect");
                    if (redirectUrl != null && !redirectUrl.isEmpty()) {
                        response.sendRedirect(redirectUrl);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/home");
                    }
                    return;
                } else {
                    errorMessage = "Invalid username or password.";
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = "An error occurred during login. Please try again.";
            }
        }

        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}

