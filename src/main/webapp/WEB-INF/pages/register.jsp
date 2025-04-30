<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css?v=1.1">
</head>
<body>
    <div class="container">
        <div class="registration-form">
            <h2>Register</h2>
            <form id="registration-form" action="${pageContext.request.contextPath}/register" method="post">
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" required 
                        pattern="[A-Za-z\s]+" title="Only letters and spaces allowed">
                    <div id="firstName-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" required
                        pattern="[A-Za-z\s]+" title="Only letters and spaces allowed">
                    <div id="lastName-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                    <div id="username-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="birthday">Birthday:</label>
                    <input type="date" id="birthday" name="birthday" required>
                    <div id="birthday-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <div class="gender-options">
                        <input type="radio" id="male" name="gender" value="male" required>
                        <label for="male">Male</label>
                        <input type="radio" id="female" name="gender" value="female" required>
                        <label for="female">Female</label>
                        <input type="radio" id="other" name="gender" value="other" required>
                        <label for="other">Other</label>
                    </div>
                    <div id="gender-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                    <div id="email-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" required>
                    <div id="phoneNumber-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                    <div id="password-error" class="error-message"></div>
                </div>
                <div class="form-group">
                    <label for="retypePassword">Retype Password:</label>
                    <input type="password" id="retypePassword" name="retypePassword" required>
                    <div id="retypePassword-error" class="error-message"></div>
                </div>
                <button type="submit" class="submit-button">Submit</button>
            </form>
        </div>
    </div>

    <script>
    const form = document.getElementById('registration-form');

    function validateForm() {
        let isValid = true;
        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const username = document.getElementById('username').value.trim();
        const birthday = document.getElementById('birthday').value.trim();
        const gender = document.querySelector('input[name="gender"]:checked');
        const email = document.getElementById('email').value.trim();
        const phoneNumber = document.getElementById('phoneNumber').value.trim();
        const password = document.getElementById('password').value.trim();
        const retypePassword = document.getElementById('retypePassword').value.trim();

        document.getElementById('firstName-error').textContent = firstName ? '' : 'Please enter your first name';
        document.getElementById('lastName-error').textContent = lastName ? '' : 'Please enter your last name';
        document.getElementById('username-error').textContent = username ? '' : 'Please enter a username';
        document.getElementById('birthday-error').textContent = birthday ? '' : 'Please select your birthday';
        document.getElementById('gender-error').textContent = gender ? '' : 'Please select your gender';
        document.getElementById('email-error').textContent = email ? (isValidEmail(email) ? '' : 'Please enter a valid email') : 'Please enter your email';
        document.getElementById('phoneNumber-error').textContent = phoneNumber ? (isValidPhoneNumber(phoneNumber) ? '' : 'Please enter a valid phone number') : 'Please enter your phone number';
        document.getElementById('password-error').textContent = password ? (isValidPassword(password) ? '' : 'Password must be at least 8 characters long and contain at least one letter, one number, and one special character') : 'Please enter a password';
        document.getElementById('retypePassword-error').textContent = retypePassword ? (retypePassword === password ? '' : 'Passwords do not match') : 'Please retype your password';

        isValid = isValid && !!firstName && !!lastName && !!username && !!birthday && !!gender && !!email && isValidEmail(email) && !!phoneNumber && isValidPhoneNumber(phoneNumber) && !!password && isValidPassword(password) && !!retypePassword && retypePassword === password;


        return isValid;
    }
    function isValidEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    function isValidPhoneNumber(phoneNumber) {
        const phoneRegex = /^\d{10}$/;
        return phoneRegex.test(phoneNumber);
    }

    function isValidPassword(password) {
       const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
       return passwordRegex.test(password);
    }

    form.addEventListener('submit', (event) => {
        if (!validateForm()) {
            event.preventDefault();
        }
        // otherwise, let it submit to the server
    });
    </script>
</body>
</html>
