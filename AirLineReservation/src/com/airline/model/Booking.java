package com.airline.model;

import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private int userId;
    private int flightId;
    private LocalDateTime bookingTime;
    private int seatsBooked;
    private double totalCost;

    // 🔹 Default constructor
    public Booking() {}

    // 🔹 Full constructor
    public Booking(int bookingId, int userId, int flightId, LocalDateTime bookingTime, int seatsBooked, double totalCost) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightId = flightId;
        this.bookingTime = bookingTime;
        this.seatsBooked = seatsBooked;
        this.totalCost = totalCost;
    }

    // ✅ Getters & Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", flightId=" + flightId +
                ", bookingTime=" + bookingTime +
                ", seatsBooked=" + seatsBooked +
                ", totalCost=" + totalCost +
                '}';
    }
}