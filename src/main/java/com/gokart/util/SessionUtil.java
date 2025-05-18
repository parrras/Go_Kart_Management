package com.gokart.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.gokart.model.UserModel;

public class SessionUtil {

    private static final String USER_SESSION_ATTR = "currentUser";
    private static final int SESSION_TIMEOUT = 30 * 60; // 30 minutes in seconds

    // Create user session
    public static void createUserSession(HttpServletRequest request, UserModel user) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_SESSION_ATTR, user);
        session.setAttribute("username", user.getUsername()); // Add username to session
        session.setMaxInactiveInterval(SESSION_TIMEOUT);
    }

    // Get current user from session
    public static UserModel getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (UserModel) session.getAttribute(USER_SESSION_ATTR) : null;
    }

    // Get username from session
     public static String getUsernameFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (String) session.getAttribute("username") : null;
    }

    // Check if user is logged in
    public static boolean isLoggedIn(HttpServletRequest request) {
        return getCurrentUser(request) != null;
    }

    // Check if user has specific role
    public static boolean hasRole(HttpServletRequest request, String role) {
        UserModel user = getCurrentUser(request);
        return user != null && role.equalsIgnoreCase(user.getRole());
    }

    // Invalidate session
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    // Refresh session timeout
    public static void refreshSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(SESSION_TIMEOUT);
        }
    }
}