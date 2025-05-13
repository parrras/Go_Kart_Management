package com.gokart.controller;

import com.gokart.dao.BookingDAO;
import com.gokart.model.BookingModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/booking")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;
    private static final Logger logger = Logger.getLogger(BookingController.class.getName());

    @Override
    public void init() throws ServletException {
        bookingDAO = new BookingDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/booknow.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        String kartIdStr = request.getParameter("kartId");
        String kartType = request.getParameter("kartType");
        String bookingDateStr = request.getParameter("bookingDate");
        String durationStr = request.getParameter("duration");
        String priceStr = request.getParameter("price");
        String paymentStatus = request.getParameter("paymentStatus");

        try {
            int userId = Integer.parseInt(userIdStr);
            int kartId = Integer.parseInt(kartIdStr);
            Date bookingDate = Date.valueOf(bookingDateStr);
            int duration = Integer.parseInt(durationStr);
            double price = Double.parseDouble(priceStr);

            BookingModel booking = new BookingModel(userId, bookingDate.toString(), paymentStatus, duration, price, kartId, kartType); // Pass String, not Date

            boolean success = bookingDAO.createBooking(booking);
            if (success) {
                request.setAttribute("bookingSuccess", true);
                logger.info("Booking created successfully.");
            } else {
                request.setAttribute("bookingError", "Failed to create booking. Please try again.");
                logger.warning("Failed to create booking.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("bookingError", "Invalid User ID or Kart ID or Duration or Price.");
            logger.log(Level.SEVERE, "NumberFormatException in doPost", e);
        } catch (IllegalArgumentException e) {
            request.setAttribute("bookingError", "Invalid Booking Date format.");
            logger.log(Level.SEVERE, "IllegalArgumentException in doPost", e);
        } catch (Exception e) {
            request.setAttribute("bookingError", "An unexpected error occurred. Please try again.");
            logger.log(Level.SEVERE, "Exception in doPost", e);
        } finally {
            request.getRequestDispatcher("/WEB-INF/pages/booknow.jsp").forward(request, response);
        }
    }
}
