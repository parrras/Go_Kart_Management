<%-- dashboard.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Go Kart Racing</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <%-- Header --%>
    <header>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_230710-removebg-preview.png" alt="Go Kart Racing Logo">
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

    <%-- Main Content --%>
    <main class="container">
        <h1>Admin Dashboard</h1>

        <div class="dashboard-summary">
            <div class="summary-card">
                <h3>Today's Bookings</h3>
                <p id="todays-bookings">0</p>
            </div>
            <div class="summary-card">
                <h3>New Bookings Today</h3>
                <p id="new-bookings">0</p>
            </div>
            <div class="summary-card">
                <h3>Total Revenue</h3>
                <p id="total-revenue">Rs. 0</p>
            </div>
            <div class="summary-card">
                <h3>Available Karts</h3>
                <p id="available-karts">0</p>
            </div>
        </div>


    </main>

    <%-- Footer --%>
    <footer>
        <p>&copy; <%= java.time.Year.now() %> Go Kart Racing. All rights reserved.</p>
    </footer>

    <script>
        // Get references to the placeholder elements
        const todaysBookingsDisplay = document.getElementById('todays-bookings');
        const newBookingsDisplay = document.getElementById('new-bookings');
        const totalRevenueDisplay = document.getElementById('total-revenue');
        const availableKartsDisplay = document.getElementById('available-karts');

        // --- Fetch Data and Update Dashboard ---
        function updateDashboard() {
            // Simulate fetching data from a server
            const dashboardData = {
                todaysBookings: 15,
                newBookings: 5,
                totalRevenue: 25000,
                availableKarts: 8,
                bookingHistory: [10, 20, 15, 25, 30, 28, 35]
            };

            // Update the placeholders with the fetched data
            todaysBookingsDisplay.textContent = dashboardData.todaysBookings;
            newBookingsDisplay.textContent = dashboardData.newBookings;
            totalRevenueDisplay.textContent = 'Rs. ' + dashboardData.totalRevenue;
            availableKartsDisplay.textContent = dashboardData.availableKarts;

            // Update the chart
            //updateBookingChart(dashboardData.bookingHistory);  Removed chart
        }

        // --- Chart Rendering (using Chart.js) ---
        function updateBookingChart(bookingHistory) {
            const ctx = document.getElementById('bookingChart').getContext('2d');
            const bookingChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
                    datasets: [{
                        label: 'Bookings',
                        data: bookingHistory,
                        backgroundColor: '#ff6600',
                        borderColor: '#ff6600',
                        fill: false,
                        pointRadius: 5,
                        pointHoverRadius: 8,
                        lineWidth: 3
                    }]
                },
                options: {
                    responsive: true,
                    title: {
                        display: true,
                        text: 'Booking Trend (Last 7 Days)',
                        fontColor: '#333',
                        fontSize: 16
                    },
                    legend: {
                        position: 'bottom'
                    },
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                fontColor: '#444'
                            },
                            gridLines: {
                                color: 'rgba(0, 0, 0, 0.05)'
                            }
                        }],
                        xAxes: [{
                            ticks: {
                                fontColor: '#444'
                            },
                            gridLines: {
                                color: 'rgba(0, 0, 0, 0.05)'
                            }
                        }]
                    }
                }
            });
        }

        // --- Initial Dashboard Load ---
        updateDashboard();

        // --- Optional: Refresh Data Periodically ---
        // setInterval(updateDashboard, 5000);
    </script>
</body>
</html>
