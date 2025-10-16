package com.airline.dao;
import com.airline.connection.DBConnection;
import com.airline.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // ✅ Create booking
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, flight_id, seats_booked, total_cost, booking_time) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getFlightId());
            stmt.setInt(3, booking.getSeatsBooked());
            stmt.setDouble(4, booking.getTotalCost());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Fetch all bookings by user
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("flight_id"),
                        rs.getTimestamp("booking_time").toLocalDateTime(),
                        rs.getInt("seats_booked"),
                        rs.getDouble("total_cost")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}