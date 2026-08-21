package com.smartevent.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String toEmail, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Smart Event - OTP Verification");

        message.setText(
            "Hello,\n\n" +
            "Your Smart Event OTP is: " + otp + "\n\n" +
            "This OTP is valid for verification.\n\n" +
            "Thank you,\n" +
            "Smart Event Team"
        );

        mailSender.send(message);
    }
    
    public void sendBookingConfirmation(
            String toEmail,
            String eventName,
            String location,
            String date,
            String time,
            int ticketCount,
            double totalAmount,
            Long bookingId) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Smart Event - Booking Confirmed 🎟️");

        message.setText(
            "Hello,\n\n" +
            "Your ticket has been booked successfully! 🎉\n\n" +
            "Booking ID: " + bookingId + "\n" +
            "Event: " + eventName + "\n" +
            "Location: " + location + "\n" +
            "Date: " + date + "\n" +
            "Time: " + time + "\n" +
            "Tickets: " + ticketCount + "\n" +
            "Total Amount: ₹" + totalAmount + "\n\n" +
            "Thank you for booking with Smart Event!\n\n" +
            "Smart Event Team"
        );

        mailSender.send(message);
    }
}