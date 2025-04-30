package com.gokart.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtil {
    
    private static final String REMEMBER_ME_COOKIE = "rememberMe";
    private static final int REMEMBER_ME_AGE = 7 * 24 * 60 * 60; // 7 days in seconds
    private static final boolean SECURE_COOKIE = true; // Set to true in production
    private static final boolean HTTP_ONLY = true;
    private static final String SAME_SITE = "Strict";

    // Create secure cookie
    public static void createCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setSecure(SECURE_COOKIE);
        cookie.setHttpOnly(HTTP_ONLY);
        // SameSite attribute needs to be set via response header
        response.addHeader("Set-Cookie", 
            String.format("%s=%s; Path=/; Max-Age=%d; %s; %s; SameSite=%s",
                name, value, maxAge,
                SECURE_COOKIE ? "Secure" : "",
                HTTP_ONLY ? "HttpOnly" : "",
                SAME_SITE));
    }

    // Create remember me cookie
    public static void createRememberMeCookie(HttpServletResponse response, String token) {
        createCookie(response, REMEMBER_ME_COOKIE, token, REMEMBER_ME_AGE);
    }

    // Get cookie by name
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(name))
                .findFirst();
        }
        return Optional.empty();
    }

    // Get remember me cookie
    public static Optional<String> getRememberMeToken(HttpServletRequest request) {
        return getCookie(request, REMEMBER_ME_COOKIE)
            .map(Cookie::getValue);
    }

    // Delete cookie
    public static void deleteCookie(HttpServletResponse response, String name) {
        createCookie(response, name, "", 0);
    }

    // Delete remember me cookie
    public static void deleteRememberMeCookie(HttpServletResponse response) {
        deleteCookie(response, REMEMBER_ME_COOKIE);
    }

    // Validate cookie value (basic example)
    public static boolean isValidCookieValue(String value) {
        // Add proper validation logic
        return value != null && !value.isEmpty() && value.length() < 255;
    }
}
