<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About GoKart Management</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

    <!-- Directly using header like in home.jsp -->
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

    <div class="about-container">
        <div class="about-hero">
            <h1>About GoKart Management</h1>
            <p>Your premier destination for high-speed karting experiences</p>
        </div>

        <div class="about-content">
            <div class="about-section">
                <div class="about-text">
                    <h2>Our Story</h2>
                    <p>Founded in 2023, GoKart Management was born from a passion for racing and technology. We've combined cutting-edge track management software with world-class facilities to deliver unforgettable racing experiences.</p>
                </div>
                <div class="about-image">
                    <img src="${pageContext.request.contextPath}/resources/images/Screenshot%202025-04-21%20200613.png" alt="Our Race Track">
                </div>
            </div>

            <div class="about-section reverse">
                <div class="about-text">
                    <h2>Our Mission</h2>
                    <p>To provide safe, exciting, and accessible karting experiences while revolutionizing track management through innovative technology solutions for operators and enthusiasts alike.</p>
                </div>
                <div class="about-image">
                    <img src="${pageContext.request.contextPath}/resources/images/Screenshot%202025-04-21%20200906.png" alt="Our Karts">
                </div>
            </div>

            <div class="stats-section">
                <div class="stat-card">
                    <h3>5+</h3>
                    <p>Locations</p>
                </div>
                <div class="stat-card">
                    <h3>50+</h3>
                    <p>High-Performance Karts</p>
                </div>
                <div class="stat-card">
                    <h3>10,000+</h3>
                    <p>Satisfied Racers</p>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; ${java.time.Year.now()} Go Kart Racing. All rights reserved.</p>
    </footer>

</body>
</html>
