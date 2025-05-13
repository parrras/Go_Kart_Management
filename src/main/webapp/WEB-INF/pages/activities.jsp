<%-- activity.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activities</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/activity.css">
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

    <main class="activity-page">
        <section class="activity-section">
            <div class="activity-container">
                <h2>There is more than just gokarting</h2>
                <div class="activity-grid">
                    <div class="activity-card">
                        <img src="${pageContext.request.contextPath}/resources/images/Screenshot%202025-04-14%20234932.png" alt="Arcade">
                        <h3>Arcade</h3>
                        <p>Step into a world of thrilling entertainment where the adrenaline-pumping go-karting action is just the beginning! Beyond the roar of the engines, our vibrant arcade beckons with a dazzling array of games. From nostalgic classics that spark fond memories to the latest immersive video adventures, there's a challenge for every gamer. Test your precision at skill-based prize redemption games, engage in fast-paced battles on the air hockey table, or sink those winning shots on the basketball hoops. Whether you're taking a break from the track or seeking an evening of pure fun, our arcade promises a captivating experience filled with laughter, friendly competition, and the thrill of victory. It's the perfect complement to the high-speed excitement of go-karting, making our venue the ultimate destination for a complete entertainment experience.</p>
                    </div>
                    <div class="activity-card">
                        <img src="${pageContext.request.contextPath}/resources/images/Screenshot%202025-04-14%20235239.png" alt="Cafes and Restaurants">
                        <h3>Cafes and Restaurants</h3>
                        <p>Following the high-octane thrills of the go-kart track and the engaging excitement of the arcade, our welcoming cafe and restaurant provides the perfect haven to revitalize. Step into a comfortable and inviting space where you can indulge in a diverse menu designed to satisfy any craving. From hearty and fulfilling main courses to lighter snacks and energizing refreshments, we offer a delicious array of options to replenish your energy after your adventures. Share stories of your racing triumphs and gaming victories over tasty appetizers, or settle in for a satisfying meal that will leave you feeling refreshed and ready for more fun. It's the ideal spot to unwind, connect with friends, and savor the moments, ensuring your visit to our entertainment center is a truly well-rounded and enjoyable experience from start to finish..</p>
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