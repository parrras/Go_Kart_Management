package com.gokart.controller;

import com.gokart.dao.UserDoa;
import com.gokart.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/usercontrol")
public class UserControlController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDoa userDoa;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void init() throws ServletException {
        userDoa = new UserDoa();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserModel> users = userDoa.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/user_control.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            addUser(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            updateUser(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            deleteUser(request, response);
        } else if ("search".equalsIgnoreCase(action)) {
            searchUsers(request, response);
        } else {
            // Handle other cases or show all users by default
            doGet(request, response);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String birthdayStr = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");  // Make sure you get password
        String role = "racer";

        UserModel newUser = new UserModel();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        try {
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                Date parsedDate = sdf.parse(birthdayStr);
                newUser.setBirthday(sdf.format(parsedDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
             request.setAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
             doGet(request, response);
             return;
        }
        newUser.setGender(gender);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password); 
        newUser.setRole(role);

        userDoa.addUser(newUser);
        response.sendRedirect(request.getContextPath() + "/usercontrol"); // Redirect to user control page
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userID"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String birthdayStr = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String role = request.getParameter("role");

        UserModel updatedUser = new UserModel();
        updatedUser.setUserID(userId);
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setUsername(username);
        try {
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                 Date parsedDate = sdf.parse(birthdayStr);
                updatedUser.setBirthday(sdf.format(parsedDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
            doGet(request, response);
            return;
        }
        updatedUser.setGender(gender);
        updatedUser.setEmail(email);
        updatedUser.setPhoneNumber(phoneNumber);
        updatedUser.setRole(role);

        userDoa.updateUser(updatedUser);
        response.sendRedirect(request.getContextPath() + "/usercontrol");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        userDoa.deleteUser(firstName, lastName);
        response.sendRedirect(request.getContextPath() + "/usercontrol");
    }


    private void searchUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        List<UserModel> allUsers = userDoa.getAllUsers();  // Get all users
        List<UserModel> filteredUsers = new ArrayList<>();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            for (UserModel user : allUsers) {
                if (user.getFirstName().toLowerCase().contains(searchTerm.toLowerCase())
                        || user.getLastName().toLowerCase().contains(searchTerm.toLowerCase())
                        || user.getUsername().toLowerCase().contains(searchTerm.toLowerCase())
                        || user.getEmail().toLowerCase().contains(searchTerm.toLowerCase())
                        || user.getPhoneNumber().contains(searchTerm)) {
                    filteredUsers.add(user);
                }
            }
            request.setAttribute("users", filteredUsers);
        } else {
             request.setAttribute("users", allUsers);
        }

        request.getRequestDispatcher("/WEB-INF/pages/user_control.jsp").forward(request, response);
    }
}