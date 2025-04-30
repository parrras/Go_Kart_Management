<%-- home.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Go Kart Racing</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
	<header>
		<div class="logo-container">
			<img
				src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_230710-removebg-preview.png"
				alt="Go Kart Racing Logo">
		</div>
		<nav>
			<ul class="nav-links">
				<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            	<li><a href="${pageContext.request.contextPath}/karts">Karts</a></li>
            	<li><a href="${pageContext.request.contextPath}/pricing">Pricing</a></li>
            	<li><a href="${pageContext.request.contextPath}/contactUs">Contact Us</a></li>
            	<li><a href="${pageContext.request.contextPath}/activities">Activities</a></li>
            	<li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
				<li><a href="#" class="book-now-button">Book Now</a></li>
			</ul>
		</nav>
	</header>

	<main>
		<section class="hero-section">
			<div class="hero-content">
				<h1>READY TO RACE?</h1>
				<p class="hero-description">Experience the thrill of speed with
					our easy-to-use Go-Kart Booking System. Whether you're racing solo
					or planning a fun day out with friends, our platform lets you
					browse available karts, book your slot instantly, and track your
					ride details — all in one place. With real-time availability,
					secure payment, and user-friendly navigation, we make sure your
					go-karting adventure starts before you even hit the track. Fast.
					Fun. Effortless. Let's race!</p>
			</div>
		</section>

		<section class="karts-section">
			<div class="karts-container">
				<h2>Our Karts</h2>
				<div class="kart-grid">
					<div class="kart-card">
						<img
							src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_232252-removebg-preview.png"
							alt="Standard Kart">
						<h3>Standard Kart</h3>
						<p>Perfect for beginners, easy to handle.</p>
					</div>
					<div class="kart-card">
						<img
							src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_233010-removebg-preview.png"
							alt="Pro Kart">
						<h3>Pro Kart</h3>
						<p>For experienced racers, higher speeds.</p>
					</div>
					<div class="kart-card">
						<img
							src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_232210-removebg-preview.png"
							alt="Double Kart">
						<h3>Double Kart</h3>
						<p>Bring a friend for twice the fun!</p>
					</div>
				</div>
			</div>
		</section>
	</main>

	<footer>
		<p>&copy; ${java.time.Year.now()} Go Kart Racing. All rights
			reserved.</p>
	</footer>

	<script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>