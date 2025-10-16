package com.airline.dao;

import com.airline.connection.DBConnection;
import com.airline.model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("flight_number"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getTimestamp("departure_time").toLocalDateTime(),
                        rs.getTimestamp("arrival_time").toLocalDateTime(),
                        rs.getInt("seats_available"),
                        rs.getDouble("price")
                );
                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public Flight getFlightById(int flightId) {
        String sql = "SELECT * FROM flights WHERE flight_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, flightId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("flight_number"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getTimestamp("departure_time").toLocalDateTime(),
                        rs.getTimestamp("arrival_time").toLocalDateTime(),
                        rs.getInt("seats_available"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Reduce seat count after booking
    public boolean updateSeats(int flightId, int newSeats) {
        String sql = "UPDATE flights SET seats_available = ? WHERE flight_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newSeats);
            stmt.setInt(2, flightId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}