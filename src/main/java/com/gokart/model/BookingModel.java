package com.gokart.model;

public class BookingModel {
    private int bookingID;
    private int userID;
    private String bookingDate;
    private String paymentStatus;
    private int duration;
    private double price;
    private int kartID;
    private String KartType;

    // Default constructor
    public BookingModel() {
    }

    // Constructor with fields
    public BookingModel(int userID, String bookingDate, String paymentStatus, int duration, double price, int kartID, String KartType) {
        this.userID = userID;
        this.bookingDate = bookingDate;
        this.paymentStatus = paymentStatus;
        this.duration = duration;
        this.price = price;
        this.kartID = kartID;
        this.KartType = KartType;

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
    
    public String getkartType() {
        return KartType;
    }

    public void setkartType(String KartType) {
        this.KartType = KartType;
    }

    
}
