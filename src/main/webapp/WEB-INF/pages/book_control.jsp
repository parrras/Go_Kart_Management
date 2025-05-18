<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.gokart.model.BookingModel" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Control Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/book_control.css">
</head>
<body>
    <header>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_230710-removebg-preview.png" alt="GoKart Logo">
        </div>
        <nav>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/usercontrol">User Control</a></li>
                <li><a href="${pageContext.request.contextPath}/bookcontrol">Book Control</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
            </ul>
        </nav>
    </header>

    <main class="dashboard-content">
        <h1>Booking Control Dashboard</h1>

        <div class="search-section">
            <form action="${pageContext.request.contextPath}/bookcontrol" method="post">
                <input type="hidden" name="action" value="search">
                <input type="text" id="search-box" name="searchTerm" placeholder="Search Bookings...">
                <button id="search-button" type="submit">Search</button>
                <button id="show-all-button" type="button" onclick="window.location.href='${pageContext.request.contextPath}/bookcontrol'">Show All</button>
            </form>
        </div>

        <div class="booking-list-container">
            <table id="booking-list">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>User ID</th>
                        <th>Kart ID</th>
                        <th>Booking Date</th>
                        <th>Duration</th>
                        <th>Price</th>
                        <th>Payment Status</th>
                        <th>Kart Type</th>
                    </tr>
                </thead>
                <tbody id="booking-table-body">
                    <%
                        List<BookingModel> bookings = (List<BookingModel>) request.getAttribute("bookings");
                        if (bookings != null) {
                            for (BookingModel booking : bookings) {
                    %>
                        <tr data-booking-id="<%= booking.getBookingID() %>">
                            <td><%= booking.getBookingID() %></td>
                            <td><%= booking.getUserID() %></td>
                            <td><%= booking.getKartID() %></td>
                            <td><%= booking.getBookingDate() %></td>
                            <td><%= booking.getDuration() %></td>
                            <td><%= booking.getPrice() %></td>
                            <td><%= booking.getPaymentStatus() %></td>
                            <td><%= booking.getkartType() %></td>
                        </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>

        <div class="form-container">
            <h2>Booking Form</h2>
            <form id="booking-form" action="${pageContext.request.contextPath}/bookcontrol" method="post">
                <input type="hidden" name="action" id="form-action" value="add">
                <div class="form-row">
                    <label for="booking-id">Booking ID:</label>
                    <input type="text" id="booking-id" name="bookingId" readonly>
                </div>
                <div class="form-row">
                    <label for="user-id">User ID:</label>
                    <input type="text" id="user-id" name="userId" required>
                </div>
                <div class="form-row">
                    <label for="kart-id">Kart ID:</label>
                    <input type="text" id="kart-id" name="kartID" required> </div>
                <div class="form-row">
                    <label for="booking-date">Booking Date:</label>
                    <input type="date" id="booking-date" name="bookingDate" required>
                </div>
                <div class="form-row">
                    <label for="duration">Duration (minutes):</label>
                    <input type="number" id="duration" name="duration" required>
                </div>
                <div class="form-row">
                    <label for="price">Price:</label>
                    <input type="number" id="price" name="price" required>
                </div>
                <div class="form-row">
                    <label for="payment-status">Payment Status:</label>
                    <select id="payment-status" name="paymentStatus">
                        <option value="online">Online</option>
                        <option value="cash">Cash</option>
                    </select>
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
                <div class="form-actions">
                    <button type="submit" id="add-button">ADD</button>
                    <button type="submit" id="update-button">UPDATE</button>
                    <button type="submit" id="delete-button">DELETE</button>
                    <button type="reset" id="clear-button">CLEAR</button>
                </div>
            </form>
        </div>
    </main>

    <footer>
        <p>&copy; <%= java.time.Year.now() %> Go Kart Racing. All rights reserved.</p>
    </footer>

    <script>
        const bookingListTable = document.getElementById("booking-list");
        const bookingForm = document.getElementById("booking-form");
        const addBtn = document.getElementById("add-button");
        const updateBtn = document.getElementById("update-button");
        const deleteBtn = document.getElementById("delete-button");
        const bookingIdInput = document.getElementById("booking-id");
        const userIdInput = document.getElementById("user-id");
        const kartIdInput = document.getElementById("kart-id");
        const bookingDateInput = document.getElementById("booking-date");
        const durationInput = document.getElementById("duration");
        const priceInput = document.getElementById("price");
        const paymentStatusInput = document.getElementById("payment-status");
        const formActionInput = document.getElementById("form-action");
        const kartTypeInput = document.getElementById("kart-type");

        let selectedRow = null;

        bookingListTable.addEventListener("click", (event) => {
            selectedRow = event.target.closest('tr');
            if (selectedRow) {
                bookingIdInput.value = selectedRow.cells[0].textContent;
                userIdInput.value = selectedRow.cells[1].textContent;
                kartIdInput.value = selectedRow.cells[2].textContent;
                bookingDateInput.value = selectedRow.cells[3].textContent;
                durationInput.value = selectedRow.cells[4].textContent;
                priceInput.value = selectedRow.cells[5].textContent;
                paymentStatusInput.value = selectedRow.cells[6].textContent;
                kartTypeInput.value = selectedRow.cells[7].textContent;
                formActionInput.value = "update";

                addBtn.textContent = "Update Booking";
                updateBtn.style.display = "none";
                deleteBtn.style.display = "inline-block";
            }
        });

        function clearForm() {
            formActionInput.value = "add";
            bookingIdInput.value = "";
            userIdInput.value = "";
            kartIdInput.value = "";
            bookingDateInput.value = "";
            durationInput.value = "";
            priceInput.value = "";
            paymentStatusInput.value = "";
            kartTypeInput.value = "";

            addBtn.textContent = "ADD";
            updateBtn.style.display = "inline-block";
            deleteBtn.style.display = "none";
            selectedRow = null;
        }

        addBtn.addEventListener("click", (event) => {
            if (formActionInput.value === "add") {
                bookingForm.submit();
            } else {
                formActionInput.value = "update";
                bookingForm.submit();
            }
        });

        updateBtn.addEventListener("click", (event) => {
            formActionInput.value = "update";
            bookingForm.submit();
        });

        deleteBtn.addEventListener("click", (event) => {
            if (selectedRow) {
                formActionInput.value = "delete";
                // Only keep kartID; remove unnecessary values
                bookingIdInput.value = "";
                userIdInput.value = "";
                bookingDateInput.value = "";
                durationInput.value = "";
                priceInput.value = "";
                paymentStatusInput.value = "";
                kartTypeInput.value = "";

                bookingForm.submit();
            } else {
                alert("Please select a booking to delete.");
            }
        });

        document.getElementById("clear-button").addEventListener("click", clearForm);
    </script>
</body>
</html>