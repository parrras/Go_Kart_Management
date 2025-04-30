<%-- contact.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contactUs.css">
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
            	<li><a href="${pageContext.request.contextPath}/contactUs">Contact Us</a></li>
            	<li><a href="${pageContext.request.contextPath}/activities">Activities</a></li>
            	<li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
                <li><a href="#" class="book-now-button">Book Now</a></li>
            </ul>
        </nav>
    </header>

    <main class="contact-page">
        <section class="contact-section">
            <div class="contact-container">
                <h2>CONTACT US</h2>
                <p>Contact the Go Kart Island Grand Prix Circuit Visitor Centre & Go Karts</p>
                <div class="contact-details">
                    <p>Phone: +99 9 9999 9900</p>
                    <p>Email: <a href="mailto:vic@phillipislandcircuit.com.au">gokartmanagementcircuit.com.au</a></p>
                    <p>We value your enquiries & feedback & will respond within 48 hours from the time your enquiry is sent.</p>
                </div>

                <form class="contact-form">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="name">Name <span class="required">*</span></label>
                            <input type="text" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email <span class="required">*</span></label>
                            <input type="email" id="email" name="email" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="tel" id="phone" name="phone">
                        </div>
                        <div class="form-group">
                            <label for="enquiry-type">Enquiry Type</label>
                            <select id="enquiry-type" name="enquiry-type">
                                <option value="general">General Enquiry</option>
                                <option value="booking">Booking Enquiry</option>
                                <option value="feedback">Feedback</option>
                                <option value="other">Other</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="message">Message <span class="required">*</span></label>
                        <textarea id="message" name="message" rows="5" required></textarea>
                    </div>
                    <button type="submit" class="submit-button">Send Enquiry</button>
                </form>

                <div class="address-details">
                    <div class="address-box">
                        <h3>Circuit Located</h3>
                        <p>999 street New York City,</p>
                        <p>The Bronx,</p>
                        <p>USA</p>
                        <a href="#" class="directions-link">Get directions</a>
                    </div>
                    <div class="address-box">
                        <h3>Postal Address</h3>
                        <p>RMB 500GP</p>
                        <p>The bronx, NYC 3922</p>
                        <p>America</p>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <footer>
        <p>&copy; ${java.time.Year.now()} Go Kart Racing. All rights reserved.</p>
    </footer>
    <script>
    document.querySelector('.contact-form').addEventListener('submit', function(event) {
        event.preventDefault();

        // Get form values
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const message = document.getElementById('message').value;

        // Simple validation
        if (!name || !email || !message) {
            alert('Please fill in all required fields.'); // Basic error
            return;
        }



        // You would do an AJAX call here to send the data to your server
        console.log('Form Data:', { name, email,  message });
        alert('Form submitted! (Check console for data)');

        // Clear the form (optional)
        document.getElementById('name').value = '';
        document.getElementById('email').value = '';
        document.getElementById('phone').value = '';
        document.getElementById('enquiry-type').value = 'general';
        document.getElementById('message').value = '';


    });
    </script>
</body>
</html>