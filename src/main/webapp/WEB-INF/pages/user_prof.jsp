<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - Go Kart Management</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_prof.css">
</head>
<body>

    <header>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_230710-removebg-preview.png" alt="Go Kart Racing Logo">
        </div>
        <nav>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/karts">Karts</a></li>
                <li><a href="${pageContext.request.contextPath}/pricing">Pricing</a></li>
                <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
                <li><a href="${pageContext.request.contextPath}/contactUs">Contact Us</a></li>
                <li><a href="${pageContext.request.contextPath}/activities">Activities</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/userprofile" class="profile-button">
                        <i class="fas fa-user"></i>
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/booking" class="book-now-button">Book Now</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h1>User Profile</h1>

        <c:if test="${not empty user}">
            <div class="profile-section">
                <div class="profile-info">
                    <div class="info-block">
                        <p><strong>Name:</strong> ${user.firstName} ${user.lastName}</p>
                        <p><strong>Date Of Birth:</strong> <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></p>
                        <p><strong>Email:</strong> ${user.email}</p>
                    </div>
                    <div class="info-block">
                        <p><strong>User Name:</strong> ${user.username}</p>
                        <p><strong>Phone Number:</strong> ${user.phoneNumber}</p>
                        </div>
                </div>

                <div class="profile-avatar">
                    <img src="${pageContext.request.contextPath}/images/default-avatar.png" alt="User Avatar">
                </div>
            </div>

            <div class="profile-actions">
                <a href="${pageContext.request.contextPath}/userprofile?action=edit" class="update-button">Update Profile</a>
                <button class="logout-button" id="logout-button">Logout</button>
            </div>
        </c:if>

    </div>

    <footer>
        <p>&copy; ${java.time.Year.now()} Go Kart Racing. All rights reserved.</p>
    </footer>

    <script>
        const logoutButton = document.getElementById('logout-button');
        logoutButton.addEventListener('click', function () {
            window.location.href = "${pageContext.request.contextPath}/logout";
        });
    </script>

</body>
</html>
