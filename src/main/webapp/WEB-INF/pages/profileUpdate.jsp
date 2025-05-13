<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Profile - Go Kart Management</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileUpdate.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

    <div class="container">
        <h1>Update Profile</h1>

        <c:if test="${not empty user}">
            <form action="${pageContext.request.contextPath}/updateprofile" method="post" class="update-form">
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" value="${user.firstName}" required>
                </div>

                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" value="${user.lastName}" required>
                </div>

                <div class="form-group">
                    <label for="username">User Name:</label>
                    <input type="text" id="username" name="username" value="${user.username}" readonly>
                    <small>Username cannot be changed.</small>
                </div>

                <div class="form-group">
                    <label for="birthday">Date Of Birth:</label>
                    <input type="date" id="birthday" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>">
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.email}" required>
                </div>

                <div class="form-group">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}">
                </div>

                <button type="submit" class="save-button">Save Changes</button>
                <a href="${pageContext.request.contextPath}/userprofile" class="cancel-button">Cancel</a>
            </form>
        </c:if>
        <c:if test="${empty user}">
            <p>Could not retrieve user information for updating.</p>
            <p><a href="${pageContext.request.contextPath}/userprofile">Go back to profile</a></p>
        </c:if>
    </div>

</body>
</html>
