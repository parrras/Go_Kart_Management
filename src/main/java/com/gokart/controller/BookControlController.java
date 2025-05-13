package com.gokart.controller;

import com.gokart.dao.BookingDAO;
import com.gokart.model.BookingModel; // Use BookingModel
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
        List<BookingModel> bookings = bookingDAO.getAllBookings(); // Use BookingModel
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
            List<BookingModel> allBookings = bookingDAO.getAllBookings(); // Use BookingModel
             List<BookingModel> filteredBookings = new ArrayList<>();  // Use BookingModel
            try{
                int searchId = Integer.parseInt(searchTerm);
                 for (BookingModel booking : allBookings) {  // Use BookingModel
                    if (booking.getBookingID() == searchId || booking.getUserID() == searchId || booking.getKartID() == searchId) {
                        filteredBookings.add(booking);
                    }
                 }
                request.setAttribute("bookings", filteredBookings);
            }catch(NumberFormatException e){
                 for (BookingModel booking : allBookings) { // Use BookingModel
                    if (booking.getPaymentStatus().toLowerCase().contains(searchTerm.toLowerCase()) || booking.getBookingDate().toString().contains(searchTerm)) {
                        filteredBookings.add(booking);
                    }
                 }
                  request.setAttribute("bookings", filteredBookings);
            }
           
        }else{
             List<BookingModel> bookings = bookingDAO.getAllBookings();  // Use BookingModel
             request.setAttribute("bookings", bookings);
        }
       
        request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int kartId = Integer.parseInt(request.getParameter("kartId"));
        String bookingDate = request.getParameter("bookingDate");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double price = Double.parseDouble(request.getParameter("price"));
        String paymentStatus = request.getParameter("paymentStatus");
        String kartType = request.getParameter("kartType");

        BookingModel booking = new BookingModel(userId, bookingDate, paymentStatus, duration, price, kartId, kartType); // Removed bookingId
        bookingDAO.createBooking(booking);

        List<BookingModel> bookings = bookingDAO.getAllBookings();  // Use BookingModel
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int kartId = Integer.parseInt(request.getParameter("kartId"));
        String bookingDate = request.getParameter("bookingDate");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double price = Double.parseDouble(request.getParameter("price"));
        String paymentStatus = request.getParameter("paymentStatus");
        String kartType = request.getParameter("kartType");

        BookingModel booking = new BookingModel(userId, bookingDate, paymentStatus, duration, price, kartId, kartType);
        booking.setBookingID(bookingId);
        bookingDAO.updateBooking(booking);

        List<BookingModel> bookings = bookingDAO.getAllBookings();  // Use BookingModel
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kartIdParam = request.getParameter("kartId");
        if (kartIdParam != null && !kartIdParam.isEmpty()) {
            try {
                int kartId = Integer.parseInt(kartIdParam);
                bookingDAO.deleteBookingByKartId(kartId);

                List<BookingModel> bookings = bookingDAO.getAllBookings();
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("/WEB-INF/pages/book_control.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where the kartId is not a valid integer
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid kart ID format."); //send error response
                return; // IMPORTANT: Stop processing to prevent further errors
            }

        } else {
            // Handle the case where the kartId parameter is missing or empty
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Kart ID is missing.");  // Send error response
            return; // IMPORTANT: Stop processing
        }
    }

}
