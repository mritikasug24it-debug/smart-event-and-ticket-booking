package com.smartevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartevent.entity.Booking;
import com.smartevent.entity.Event;
import com.smartevent.repository.BookingRepository;
import com.smartevent.repository.EventRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmailService emailService;

    public Booking saveBooking(Booking booking) {

        Event event = eventRepository
                .findById(booking.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Check ticket count
        if (booking.getTicketCount() <= 0) {
            throw new RuntimeException("Ticket count must be greater than 0");
        }

        // Check available tickets
        if (booking.getTicketCount() > event.getAvailableTickets()) {
            throw new RuntimeException("Not enough tickets available");
        }

        // Calculate total amount
        double totalAmount =
                booking.getTicketCount() * event.getPrice();

        booking.setEventName(event.getName());
        booking.setTotalAmount(totalAmount);

        // Reduce available tickets
        event.setAvailableTickets(
                event.getAvailableTickets() - booking.getTicketCount()
        );

        // Save updated event
        eventRepository.save(event);

        // Save booking first
        Booking savedBooking = bookingRepository.save(booking);

        // Send booking confirmation email
        emailService.sendBookingConfirmation(
                savedBooking.getUserEmail(),
                event.getName(),
                event.getLocation(),
                event.getDate(),
                event.getTime(),
                savedBooking.getTicketCount(),
                savedBooking.getTotalAmount(),
                savedBooking.getId()
        );

        return savedBooking;
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}