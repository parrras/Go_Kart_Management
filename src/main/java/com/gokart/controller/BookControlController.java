package com.gokart.controller;

import com.gokart.dao.BookingDAO;
import com.gokart.model.BookingModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bookcontrol")
public class BookControlController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        bookingDAO = new BookingDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookingModel> bookings = bookingDAO.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            doGet(request, response);
            return;
        }
        switch (action) {
            case "add":
                addBooking(request, response);
                break;
            case "update":
                updateBooking(request, response);
                break;
            case "delete":
                deleteBooking(request, response);
                break;
            case "search":
                searchBooking(request, response);
                break;
            default:
                doGet(request, response);
        }
    }

    private void searchBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm != null && !searchTerm.isEmpty()) {
            List<BookingModel> allBookings = bookingDAO.getAllBookings();
            List<BookingModel> filteredBookings = new ArrayList<>();
            try{
                int searchId = Integer.parseInt(searchTerm);
                for (BookingModel booking : allBookings) {
                    if (booking.getBookingID() == searchId || booking.getUserID() == searchId || booking.getKartID() == searchId) {
                        filteredBookings.add(booking);
                    }
                }
                request.setAttribute("bookings", filteredBookings);
            }catch(NumberFormatException e){
                for (BookingModel booking : allBookings) {
                    if (booking.getPaymentStatus().toLowerCase().contains(searchTerm.toLowerCase()) || booking.getBookingDate().toString().contains(searchTerm)) {
                        filteredBookings.add(booking);
                    }
                }
                request.setAttribute("bookings", filteredBookings);
            }

        }else{
            List<BookingModel> bookings = bookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);
        }

        request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int kartId = Integer.parseInt(request.getParameter("kartID"));
            String bookingDate = request.getParameter("bookingDate");
            int duration = Integer.parseInt(request.getParameter("duration"));
            double price = Double.parseDouble(request.getParameter("price"));
            String paymentStatus = request.getParameter("paymentStatus");
            String kartType = request.getParameter("kartType");

            BookingModel booking = new BookingModel(userId, bookingDate, paymentStatus, duration, price, kartId, kartType);
            bookingDAO.createBooking(booking);

            List<BookingModel> bookings = bookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format. Please check User ID, Kart ID, Duration, and Price.");
            return;
        }
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int kartId = Integer.parseInt(request.getParameter("kartID")); // Corrected parameter name to kartID
            String bookingDate = request.getParameter("bookingDate");
            int duration = Integer.parseInt(request.getParameter("duration"));
            double price = Double.parseDouble(request.getParameter("price"));
            String paymentStatus = request.getParameter("paymentStatus");
            String kartType = request.getParameter("kartType");

            BookingModel booking = new BookingModel(userId, bookingDate, paymentStatus, duration, price, kartId, kartType);
            booking.setBookingID(bookingId);
            bookingDAO.updateBooking(booking);

            List<BookingModel> bookings = bookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format. Please check all fields.");
            return;
        }
    }


 private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String kartIdParam = request.getParameter("kartID"); // Corrected parameter name to kartID
    if (kartIdParam != null && !kartIdParam.isEmpty()) {
        try {
            int kartId = Integer.parseInt(kartIdParam);
            bookingDAO.deleteBookingByKartId(kartId);

            List<BookingModel> bookings = bookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle the case where the kartId is not a valid integer
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid kart ID format.");
            return;
        }

    } else {
        // Handle the case where the kartId parameter is missing or empty
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Kart ID is missing.");
        return;
    }
}

}

