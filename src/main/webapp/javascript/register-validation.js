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

    // First Name validation (only letters and spaces)
    const nameRegex = /^[A-Za-z\s]+$/;
    if (!firstName) {
        document.getElementById('firstName-error').textContent = 'Please enter your first name';
        isValid = false;
    } else if (!nameRegex.test(firstName)) {
        document.getElementById('firstName-error').textContent = 'First name can only contain letters and spaces';
        isValid = false;
    } else {
        document.getElementById('firstName-error').textContent = '';
    }

    // Last Name validation (only letters and spaces)
    if (!lastName) {
        document.getElementById('lastName-error').textContent = 'Please enter your last name';
        isValid = false;
    } else if (!nameRegex.test(lastName)) {
        document.getElementById('lastName-error').textContent = 'Last name can only contain letters and spaces';
        isValid = false;
    } else {
        document.getElementById('lastName-error').textContent = '';
    }

    // Username validation (no special characters except underscore)
    const usernameRegex = /^[A-Za-z0-9_]+$/;
    if (!username) {
        document.getElementById('username-error').textContent = 'Please enter a username';
        isValid = false;
    } else if (!usernameRegex.test(username)) {
        document.getElementById('username-error').textContent = 'Username can only contain letters, numbers and underscore';
        isValid = false;
    } else {
        document.getElementById('username-error').textContent = '';
    }

    // Rest of the validations
    document.getElementById('birthday-error').textContent = birthday ? '' : 'Please select your birthday';
    document.getElementById('gender-error').textContent = gender ? '' : 'Please select your gender';
    document.getElementById('email-error').textContent = email ? (isValidEmail(email) ? '' : 'Please enter a valid email') : 'Please enter your email';
    document.getElementById('phoneNumber-error').textContent = phoneNumber ? (isValidPhoneNumber(phoneNumber) ? '' : 'Please enter a valid phone number') : 'Please enter your phone number';
    document.getElementById('password-error').textContent = password ? (isValidPassword(password) ? '' : 'Password must be at least 8 characters long and contain at least one letter, one number, and one special character') : 'Please enter a password';
    document.getElementById('retypePassword-error').textContent = retypePassword ? (retypePassword === password ? '' : 'Passwords do not match') : 'Please retype your password';

    isValid = isValid && !!birthday && !!gender && !!email && isValidEmail(email) && 
              !!phoneNumber && isValidPhoneNumber(phoneNumber) && !!password && 
              isValidPassword(password) && !!retypePassword && retypePassword === password;

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

document.getElementById('registration-form').addEventListener('submit', function(event) {
    if (!validateForm()) {
        event.preventDefault();
    }
});
