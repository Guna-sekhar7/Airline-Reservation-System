package com.airline.utils;

import com.airline.model.Booking;
import com.airline.model.Flight;
import com.airline.model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class TicketPDFGenerator {

    public static String generateTicket(User user, Flight flight, Booking booking) {
        String fileName = "Ticket_" + booking.getBookingId() + ".pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("✈ Airline Ticket", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Passenger: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Flight: " + flight.getFlightNumber() + " (" + flight.getSource() + " → " + flight.getDestination() + ")"));
            document.add(new Paragraph("Departure: " + flight.getDepartureTime()));
            document.add(new Paragraph("Arrival: " + flight.getArrivalTime()));
            document.add(new Paragraph("Seats Booked: " + booking.getSeatsBooked()));
            document.add(new Paragraph("Total Cost: ₹" + booking.getTotalCost()));
            document.add(new Paragraph("Booking Time: " + booking.getBookingTime()));

            document.close();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}