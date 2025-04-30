package com.gokart.model;

public class BookingModel {
    private int bookingID;
    private int userID;
    private String bookingDate;
    private String paymentStatus;
    private int duration;
    private double price;
    private int kartID;

    // Default constructor
    public BookingModel() {
    }

    // Constructor with fields
    public BookingModel(int userID, String bookingDate, String paymentStatus, int duration, double price, int kartID) {
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.paymentStatus = paymentStatus;
        this.duration = duration;
        this.price = price;
        this.kartID = kartID;
    }

    // Getters and Setters
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getKartID() {
        return kartID;
    }

    public void setKartID(int kartID) {
        this.kartID = kartID;
    }

    @Override
    public String toString() {
        return "BookingModel{" +
                "bookingID=" + bookingID +
                ", userID=" + userID +
                ", bookingDate='" + bookingDate + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", kartID=" + kartID +
                "}";
    }
}
