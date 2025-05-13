<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoKart Management</title>
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <header>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="GoKart Logo">
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
</body>
</html>
