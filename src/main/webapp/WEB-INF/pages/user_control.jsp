<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.gokart.model.UserModel" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.time.format.DateTimeParseException" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Control Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_control.css">
</head>
<body>
    <header>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/images/Screenshot_2025-04-14_230710-removebg-preview.png" alt="GoKart Logo">
        </div>
        <nav>
            <ul class="nav-links">
            	<li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/usercontrol">User Control</a></li>
                <li><a href="${pageContext.request.contextPath}/bookcontrol">Book Control</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>

            </ul>
        </nav>
    </header>

    <main class="dashboard-content">
        <h1>User Control Dashboard</h1>

        <div class="search-section">
            <form action="${pageContext.request.contextPath}/usercontrol" method="post">
                <input type="hidden" name="action" value="search">
                <input type="text" id="search-box" name="searchTerm" placeholder="Search Users...">
                <button id="search-button" type="submit">Search</button>
                <button id="show-all-button" type="button" onclick="window.location.href='${pageContext.request.contextPath}/usercontrol'">Show All</button>
            </form>
        </div>

        <div class="user-list-container">
            <table id="user-list">
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Contact</th>
                        <th>User ID</th>
                    </tr>
                </thead>
                <tbody id="user-table-body">
                    <%
                        List<UserModel> users = (List<UserModel>) request.getAttribute("users");
                        if (users != null) {
                            for (UserModel user : users) {
                                String birthdayStr = user.getBirthday();
                                int age = -1;
                                if (birthdayStr != null && !birthdayStr.isEmpty()) {
                                    try {
                                        LocalDate birthdayDate = LocalDate.parse(birthdayStr);
                                        LocalDate now = LocalDate.now();
                                        age = Period.between(birthdayDate, now).getYears();
                                    } catch (DateTimeParseException e) {
                                        // Handle parsing error if needed
                                    }
                                }
                    %>
                        <tr data-user-id="<%= user.getUserID() %>">
                            <td><%= user.getFirstName() %> <%= user.getLastName() %></td>
                            <td>
                                <%= age >= 0 ? String.valueOf(age) : "N/A" %>
                            </td>
                            <td><%= user.getGender() %></td>
                            <td><%= user.getPhoneNumber() %></td>
                            <td><%= user.getUserID() %></td>
                        </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>

        <div class="form-container">
            <h2>Racer Form</h2>
            <form id="racer-form" action="${pageContext.request.contextPath}/usercontrol" method="post">
                <input type="hidden" name="action" id="form-action" value="add"> <%-- add, update, delete --%>
                <div class="form-row">
                    <label for="full-name">First Name:</label>
                    <input type="text" id="full-name" name="firstName" required>
                </div>
                <div class="form-row">
                    <label for="last-name">Last Name:</label>
                    <input type="text" id="last-name" name="lastName" required>
                </div>
                <div class="form-row">
                    <label for="username">User Name:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-row">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-row">
                    <label for="birthday">Birthday:</label>
                    <input type="date" id="birthday" name="birthday" required>
                </div>
                <div class="form-row">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender">
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div class="form-row">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-row">
                    <label for="contact">Contact:</label>
                    <input type="text" id="contact" name="phoneNumber" required>
                </div>
                <div class="form-row">
                    <label for="user-id">User ID:</label>
                    <input type="number" id="user-id" name="userID" readonly>
                </div>
                <div class="form-row">
    			<label for="role">Role:</label>
    			<input type="text" id="role" name="role" required>  
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
        <p>&copy; ${java.time.Year.now()} Go Kart Racing. All rights reserved.</p>
    </footer>

    <script>
       const userListTable = document.getElementById("user-list");
        const racerForm = document.getElementById("racer-form");
        const addBtn = document.getElementById("add-button");
        const updateBtn = document.getElementById("update-button");
        const deleteBtn = document.getElementById("delete-button");
        const firstNameInput = document.getElementById("full-name");
        const lastNameInput = document.getElementById("last-name");
        const ageInput = document.getElementById("age");
        const genderInput = document.getElementById("gender");
        const contactInput = document.getElementById("contact");
        const userIdInput = document.getElementById("user-id");
        const formActionInput = document.getElementById("form-action");
        const usernameInput = document.getElementById("username");
        const passwordInput = document.getElementById("password");
        const birthdayInput = document.getElementById("birthday");
        const emailInput = document.getElementById("email");

        let selectedRow = null;

        userListTable.addEventListener("click", (event) => {
            selectedRow = event.target.closest('tr');
            if (selectedRow) {
                const fullNameParts = selectedRow.cells[0].textContent.split(" ");
                firstNameInput.value = fullNameParts[0];
                lastNameInput.value = fullNameParts.slice(1).join(" ");
                // Age is calculated server-side and displayed
                genderInput.value = selectedRow.cells[2].textContent;
                contactInput.value = selectedRow.cells[3].textContent;
                userIdInput.value = selectedRow.dataset.userId;
                formActionInput.value = "update";

                // You might want to fetch the rest of the user data for pre-filling if needed
                usernameInput.value = ""; // Consider fetching and setting
                passwordInput.value = ""; // Do not pre-fill passwords for security
                birthdayInput.value = ""; // Consider fetching and setting
                emailInput.value = "";   // Consider fetching and setting

                addBtn.textContent = "Update User";
                updateBtn.style.display = "none";
                deleteBtn.style.display = "inline-block";
            }
        });

        function clearForm() {
            formActionInput.value = "add";
            firstNameInput.value = "";
            lastNameInput.value = "";
            ageInput.value = "";
            genderInput.value = "";
            contactInput.value = "";
            userIdInput.value = "";
            usernameInput.value = "";
            passwordInput.value = "";
            birthdayInput.value = "";
            emailInput.value = "";

            addBtn.textContent = "ADD";
            updateBtn.style.display = "inline-block";
            deleteBtn.style.display = "none";
            selectedRow = null;
        }

        addBtn.addEventListener("click", (event) => {
        		formActionInput.value = (userIdInput.value) ? "update" : "add";
            	racerForm.submit();
        });

        updateBtn.addEventListener("click", (event) => {
            formActionInput.value = "update";
            racerForm.submit();
        });

        deleteBtn.addEventListener("click", (event) => {
            if (selectedRow) {
                formActionInput.value = "delete";
                racerForm.submit();
            } else {
                alert("Please select a user to delete.");
            }
        });

        document.getElementById("clear-button").addEventListener("click", clearForm);
    </script>
</body>
</html>