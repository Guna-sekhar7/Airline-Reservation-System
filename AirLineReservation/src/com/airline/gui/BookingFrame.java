package com.airline.gui;

import com.airline.dao.BookingDAO;
import com.airline.dao.FlightDAO;
import com.airline.model.Booking;
import com.airline.model.Flight;
import com.airline.model.User;
import com.airline.utils.TicketPDFGenerator;
import com.airline.utils.EmailSender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.List;

public class BookingFrame extends JFrame {
    private User loggedInUser;
    private JComboBox<String> flightComboBox;
    private JSpinner seatsSpinner;
    private JButton bookButton;

    private List<Flight> flightList;

    public BookingFrame(User user) {
        this.loggedInUser = user;
        setTitle("Flight Booking");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Fetch flights
        flightList = new FlightDAO().getAllFlights();

        // Dropdown
        flightComboBox = new JComboBox<>();
        for (Flight flight : flightList) {
            flightComboBox.addItem(flight.getFlightNumber() + " - " + flight.getSource() + " to " + flight.getDestination() +
                    " | Seats: " + flight.getSeatsAvailable() + " | Price: ₹" + flight.getPrice());
        }

        // Seat selection
        seatsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        bookButton = new JButton("Book Flight");
        bookButton.addActionListener(this::handleBooking);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Select Flight:"));
        panel.add(flightComboBox);
        panel.add(new JLabel("Seats to Book:"));
        panel.add(seatsSpinner);
        panel.add(new JLabel(""));
        panel.add(bookButton);

        add(panel, BorderLayout.CENTER);
    }

    private void handleBooking(ActionEvent e) {
        int selectedIndex = flightComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            Flight selectedFlight = flightList.get(selectedIndex);
            int seatsToBook = (Integer) seatsSpinner.getValue();

            if (selectedFlight.getSeatsAvailable() < seatsToBook) {
                JOptionPane.showMessageDialog(this, "Not enough seats available!");
                return;
            }

            double totalCost = seatsToBook * selectedFlight.getPrice();

            Booking booking = new Booking();
            booking.setUserId(loggedInUser.getUserId());        // FIXED
            booking.setFlightId(selectedFlight.getFlightId());  // FIXED
            booking.setSeatsBooked(seatsToBook);
            booking.setTotalCost(totalCost);
            booking.setBookingTime(LocalDateTime.now());

            boolean success = new BookingDAO().createBooking(booking);
            if (success) {
                JOptionPane.showMessageDialog(this, "Booking successful! Total: ₹" + totalCost);

                // ✅ Reduce seats
                new FlightDAO().updateSeats(selectedFlight.getFlightId(), selectedFlight.getSeatsAvailable() - seatsToBook);

                // ✅ Generate PDF
                String pdfPath = TicketPDFGenerator.generateTicket(loggedInUser, selectedFlight, booking);

                // ✅ Send Email
                if (pdfPath != null) {
                    EmailSender.sendEmailWithAttachment(
                            loggedInUser.getEmail(),
                            "Your Flight Ticket Confirmation",
                            "Dear " + loggedInUser.getName() + ",\n\nYour booking is confirmed. Please find your ticket attached.\n\nThank you for choosing us!",
                            pdfPath
                    );
                }

                // refresh frame
                this.dispose();
                new BookingFrame(loggedInUser).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Booking failed. Try again.");
            }
        }
    }
}