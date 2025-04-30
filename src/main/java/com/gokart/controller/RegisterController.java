package com.gokart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gokart.model.UserModel;
import com.gokart.service.RegisterService;

/**
 * Servlet implementation class RegisterController Handles user registration
 * requests.
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.registerService = new RegisterService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Serve the registration page
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Collect form data
		UserModel user = new UserModel();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setUsername(request.getParameter("username"));
		user.setBirthday(request.getParameter("birthday"));
		user.setGender(request.getParameter("gender"));
		user.setEmail(request.getParameter("email"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		user.setPassword(request.getParameter("password"));
		user.setRole("user"); // Default role set here

		String retypePassword = request.getParameter("retypePassword");

		String errorMessage = null;

		// Basic password match validation
		if (!user.getPassword().equals(retypePassword)) {
			errorMessage = "Passwords do not match.";
		} else if (registerService.usernameExists(user.getUsername())) {
			errorMessage = "Username already exists.";
		} else if (registerService.emailExists(user.getEmail())) {
			errorMessage = "Email already exists.";
		} else if (registerService.phoneNumberExists(user.getPhoneNumber())) {
			errorMessage = "Phone number already exists.";
		}

		// If there was an error, return to the registration page
		if (errorMessage != null) {
			request.setAttribute("error", errorMessage);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}

		// Try to register the user
		try {
			boolean success = registerService.register(user);

			if (success) {
				// Set success message and redirect to login page
				request.getSession().setAttribute("successMessage", "Registration successful! Please login.");
				response.sendRedirect(request.getContextPath() + "/login");
			} else {
				// Registration failed, stay on the same page
				request.setAttribute("error", "Registration failed. Please try again.");
				request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// Exception handling for unexpected errors
			e.printStackTrace();
			request.setAttribute("error", "An unexpected error occurred.");
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		}
	}
}
