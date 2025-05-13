package com.gokart.dao;

import com.gokart.config.Dbconfig;
import com.gokart.model.BookingModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDAO {

    private static final Logger logger = Logger.getLogger(BookingDAO.class.getName());

    // Default constructor
    public BookingDAO() {
    }

    public List<BookingModel> getAllBookings() {
        List<BookingModel> bookings = new ArrayList<>();
        String sql = "SELECT bookingID, userID, bookingDate, paymentStatus, duration, price, kartID, KartType FROM booking";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in getAllBookings");
                return new ArrayList<>();
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                BookingModel booking = new BookingModel();
                booking.setBookingID(resultSet.getInt("bookingID"));
                booking.setUserID(resultSet.getInt("userID"));
                booking.setBookingDate(resultSet.getString("bookingDate")); // Changed to getString
                booking.setPaymentStatus(resultSet.getString("paymentStatus"));
                booking.setDuration(resultSet.getInt("duration"));
                booking.setPrice(resultSet.getDouble("price"));
                booking.setKartID(resultSet.getInt("kartID"));
                booking.setkartType(resultSet.getString("KartType"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in getAllBookings", e);
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return bookings;
    }

    public BookingModel getBookingById(int bookingId) {
        BookingModel booking = null;
        String sql = "SELECT bookingID, userID, bookingDate, paymentStatus, duration, price, kartID, KartType FROM booking WHERE bookingID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Dbconfig.getDbConnection();
             if (connection == null) {
                logger.severe("Failed to get database connection in getBookingById");
                return null;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookingId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                booking = new BookingModel();
                booking.setBookingID(resultSet.getInt("bookingID"));
                booking.setUserID(resultSet.getInt("userID"));
                booking.setBookingDate(resultSet.getString("bookingDate")); // Changed to getString
                booking.setPaymentStatus(resultSet.getString("paymentStatus"));
                booking.setDuration(resultSet.getInt("duration"));
                booking.setPrice(resultSet.getDouble("price"));
                booking.setKartID(resultSet.getInt("kartID"));
                booking.setkartType(resultSet.getString("KartType"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in getBookingById", e);
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
        return booking;
    }

    public boolean createBooking(BookingModel booking) {
        String sql = "INSERT INTO booking (userID, bookingDate, paymentStatus, duration, price, kartID, KartType) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in createBooking");
                return false;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, booking.getUserID());
            preparedStatement.setString(2, booking.getBookingDate()); // Use String
            preparedStatement.setString(3, booking.getPaymentStatus());
            preparedStatement.setInt(4, booking.getDuration());
            preparedStatement.setDouble(5, booking.getPrice());
            preparedStatement.setInt(6, booking.getKartID());
            preparedStatement.setString(7, booking.getkartType());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in createBooking", e);
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }

    public void updateBooking(BookingModel booking) {
        String sql = "UPDATE booking SET userID = ?, bookingDate = ?, paymentStatus = ?, duration = ?, price = ?, kartID = ?, KartType = ? WHERE bookingID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in updateBooking");
                return;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, booking.getUserID());
            preparedStatement.setString(2, booking.getBookingDate());  // Use String
            preparedStatement.setString(3, booking.getPaymentStatus());
            preparedStatement.setInt(4, booking.getDuration());
            preparedStatement.setDouble(5, booking.getPrice());
            preparedStatement.setInt(6, booking.getKartID());
            preparedStatement.setString(7, booking.getkartType());
            preparedStatement.setInt(8, booking.getBookingID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in updateBooking", e);
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }

    public void deleteBookingByKartId(int kartId) {
        String sql = "DELETE FROM booking WHERE kartID = ?"; // Modified SQL to use kartID
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Dbconfig.getDbConnection();
            if (connection == null) {
                logger.severe("Failed to get database connection in deleteBookingByKartId"); // Added KartId to log
                return;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, kartId); // Use kartId
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in deleteBookingByKartId", e); // Added KartId to log
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }


    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error closing ResultSet", e);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error closing Statement", e);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error closing Connection", e);
        }
    }
}

