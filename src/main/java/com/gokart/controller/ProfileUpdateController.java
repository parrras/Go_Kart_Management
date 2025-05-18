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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/editprofile")
public class ProfileUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDoa userDAO;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDoa();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet method called in ProfileUpdateController");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username != null) {
            UserModel currentUser = userDAO.getUserByUsername(username);
            if (currentUser != null) {
                String userJson = String.format(
                    "{ \"error\": null, \"user\": { \"userID\": %d, \"firstName\": \"%s\", \"lastName\": \"%s\", \"username\": \"%s\", \"birthday\": \"%s\", \"gender\": \"%s\", \"email\": \"%s\", \"phoneNumber\": \"%s\", \"role\": \"%s\" } }",
                    currentUser.getUserID(),
                    currentUser.getFirstName(),
                    currentUser.getLastName(),
                    currentUser.getUsername(),
                    currentUser.getBirthday() != null ? currentUser.getBirthday() : "",
                    currentUser.getGender() != null ? currentUser.getGender() : "",
                    currentUser.getEmail(),
                    currentUser.getPhoneNumber() != null ? currentUser.getPhoneNumber() : "",
                    currentUser.getRole()
                );
                out.print(userJson);
            } else {
                out.print("{ \"error\": \"Unable to fetch user data from DB.\" }");
            }
        } else {
            out.print("{ \"error\": \"Session is null or username is missing.\" }");
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost method called in ProfileUpdateController");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        String loggedInUsername = (session != null) ? (String) session.getAttribute("username") : null;

        if (loggedInUsername == null) {
            System.out.println("Session is null or username is missing.");
            out.print("{ \"error\": \"Session is null or username is missing.\" }");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.flush();
            out.close();
            return;
        }

        try {
            // Fetch updated values from form
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String birthdayStr = request.getParameter("birthday");
            String gender = request.getParameter("gender");
            String role = request.getParameter("role");

            System.out.println("loggedInUsername: " + loggedInUsername);
            System.out.println("firstName: " + firstName);
            System.out.println("lastName: " + lastName);
            System.out.println("email: " + email);
            System.out.println("phoneNumber: " + phoneNumber);
            System.out.println("birthdayStr: " + birthdayStr);
            System.out.println("gender: " + gender);
            System.out.println("role: " + role);

            // Get the user from DB using the username
            UserModel user = userDAO.getUserByUsername(loggedInUsername);

            if (user != null) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                user.setGender(gender);
                user.setRole(role);

                if (birthdayStr != null && !birthdayStr.isEmpty()) {
                    Date birthdayDate = sdf.parse(birthdayStr);
                    user.setBirthday(sdf.format(birthdayDate));
                } else {
                    user.setBirthday(null);
                }

                boolean success = userDAO.updateUser(user);
                if (success) {
                    session.setAttribute("message", "Profile updated successfully.");
                    out.print("{ \"message\": \"Profile updated successfully.\" }");
                } else {
                    out.print("{ \"error\": \"Failed to update profile. Please try again.\" }");
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } else {
                out.print("{ \"error\": \"User not found. Please log in again.\" }");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            out.print("{ \"error\": \"Invalid date format. Please use YYYY-MM-DD.\" }");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{ \"error\": \"An error occurred while updating your profile. Please try again.\" }");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            out.flush();
            out.close();
        }
    }
}