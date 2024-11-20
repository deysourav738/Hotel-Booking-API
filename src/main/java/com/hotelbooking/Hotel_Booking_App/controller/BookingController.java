package com.hotelbooking.Hotel_Booking_App.controller;

import com.hotelbooking.Hotel_Booking_App.model.Booking;
import com.hotelbooking.Hotel_Booking_App.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Get all bookings (Accessible to Admin only).
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Get booking by ID (Accessible to Admin only).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") Long bookingId) {
        Optional<Booking> booking = bookingService.getBookingById(bookingId);
        return booking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create a new booking (Accessible to Admin only).
     */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    /**
     * Update an existing booking (Accessible to Admin only).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") Long bookingId, @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, booking);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    /**
     * Delete a booking by ID (Accessible to Admin only).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get bookings by hotel ID (Accessible to Admin and User).
     */
    @GetMapping("/search/hotel/{hotelId}")
    public ResponseEntity<List<Booking>> getBookingsByHotelId(@PathVariable("hotelId") Long hotelId) {
        List<Booking> bookings = bookingService.findBookingsByHotelId(hotelId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}
