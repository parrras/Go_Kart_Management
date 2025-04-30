<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login to your account</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />

</head>
<body>
	<div class="container">
		<div class="form">
		<%
		// Check for success message from registration (passed via session)
		String regSuccessMessage = (String) session.getAttribute("successMessage");
		if (regSuccessMessage != null) {
			out.println("<p class=\"success-message\">" + regSuccessMessage + "</p>");
			session.removeAttribute("successMessage"); // Remove after displaying once
		}

		// Original code follows
		String errorMessage = (String) request.getAttribute("error");
		String successMessage = (String) request.getAttribute("success");

		if (errorMessage != null && !errorMessage.isEmpty()) {
			out.println("<p class=\"error-message\">" + errorMessage + "</p>");
		}

		if (successMessage != null && !successMessage.isEmpty()) {
		%>
		<p class="success-message"><%=successMessage%></p>
		<%
		}
		%>

		<h2>Login</h2>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="username" name="username" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
			</div>
			<div class="row">
				<button type="submit" id="submit-btn">Login</button>
			</div>
		</form>

		<!-- Forgot Password and Register Button in Separate Rows -->
		<div class="row">
			<a href="${pageContext.request.contextPath}/forgotpassword"
				class="forgot-button">Forgot Password</a>
		</div>

		<div class="row">
			<a href="${pageContext.request.contextPath}/register"
				class="register-button">Create new account</a>
		</div>
	</div>
</body>
</html>
