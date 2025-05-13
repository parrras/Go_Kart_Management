package com.gokart.controller;

import com.gokart.dao.UserDoa;
import com.gokart.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editprofile")
public class ProfileUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDoa userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDoa();  // FIXED: no parameters needed
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // false = don’t create new session

        if (session == null || session.getAttribute("loggedInUsername") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String loggedInUsername = (String) session.getAttribute("loggedInUsername");
        UserModel user = userDAO.getUserByUsername(loggedInUsername);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/profileUpdate.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
