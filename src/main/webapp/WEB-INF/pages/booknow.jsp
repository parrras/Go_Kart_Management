<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Now - Go Kart Racing</title>
    <link
        href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/booknow.css">
</head>
</head>
<body>

    <main>
        <div class="booking-form-container">
            <h1>Book Your Kart</h1>
            <% if (request.getAttribute("bookingSuccess") != null) { %>
                <div style="color: green; margin-bottom: 10px;">Booking successful!</div>
            <% } %>
            <% if (request.getAttribute("bookingError") != null) { %>
                <div style="color: red; margin-bottom: 10px;"><%= request.getAttribute("bookingError") %></div>
            <% } %>
            <form id="booking-form" action="${pageContext.request.contextPath}/booking" method="post">
                <div class="form-row">
                    <label for="user-id">User ID:</label>
                    <input type="text" id="user-id" name="userId" required>
                    <p style="font-size: 0.8rem; color: #666; margin-top: 0.25rem;"></p>
                </div>
                <div class="form-row">
                    <label for="kart-id">Kart ID:</label>
                    <input type="text" id="kart-id" name="kartId" required>
                </div>
                <div class="form-row">
                    <label for="kart-type">Kart Type:</label>
                    <select id="kart-type" name="kartType">
                        <option value="standard">Standard Kart</option>
                        <option value="pro">Pro Kart</option>
                        <option value="double">Double Kart</option>
                        <option value="electric">Electric Kart</option>
                    </select>
                </div>
                <div class="form-row">
                    <label for="booking-date">Booking Date:</label>
                    <input type="date" id="booking-date" name="bookingDate" required>
                </div>
                <div class="form-row">
                    <label for="duration">Duration:</label>
                    <select id="duration" name="duration">
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                    </select>
                </div>
                <div class="form-row">
                    <label for="price">Price:</label>
                    <select id="price" name="price">
                        <option value="40">40.00</option>
                        <option value="70">70.00</option>
                        <option value="90">90.00</option>
                    </select>
                </div>
                <div class="form-row">
                    <label for="payment-status">Payment Status:</label>
                    <select id="payment-status" name="paymentStatus">
                        <option value="online">Online</option>
                        <option value="cash">Cash</option>
                    </select>
                </div>
                <div class="form-actions">
                    <button type="submit">Book Now</button>
                    <button type="reset">Clear</button>
                </div>
            </form>
        </div>
    </main>

    <footer>
        <p>&copy; <%= java.time.Year.now() %> Go Kart Racing. All rights reserved.</p>
    </footer>

    <script>
        // The form will now submit directly to the /booking servlet
    </script>
</body>
</html>
