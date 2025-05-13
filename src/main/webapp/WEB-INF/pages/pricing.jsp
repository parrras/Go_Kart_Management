<%-- pricing.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pricing</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pricing.css">
    <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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

    <main class="pricing-page">
        <section class="pricing-section">
            <div class="pricing-container">
                <h2>PRICING</h2>
                <div class="price-grid">
                    <div class="price-card">
                        <h3>1 x 10 minute session</h3>
                        <p>$40 per driver</p>
                    </div>
                    <div class="price-card">
                        <h3>2 x 10 minute sessions</h3>
                        <p>$70 per driver</p>
                    </div>
                    <div class="price-card">
                        <h3>3 x 10 minute sessions</h3>
                        <p>$90 per driver</p>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <footer>
        <p>&copy; ${java.time.Year.now()} Go Kart Racing. All rights reserved.</p>
    </footer>
</body>
</html>