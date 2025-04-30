<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>GoKart Management</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/landing.css">
</head>
<body>
    <div class="landing-container">
        <div class="welcome-message">
            <h2>Welcome to our system</h2>
            <h1>Go Kart Management</h1>
        </div>
        <div class="user-selection">
            <h3>DO YOU WANT TO</h3>
            <div class="button-group">
                <a href="${pageContext.request.contextPath}/login" class="landing-button login">LOGIN</a>
                <a href="${pageContext.request.contextPath}/register" class="landing-button register">REGISTER</a>
            </div>
        </div>
    </div>
</body>
</html>
