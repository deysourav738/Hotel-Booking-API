package com.hotelbooking.Hotel_Booking_App.service;

import com.hotelbooking.Hotel_Booking_App.model.Booking;
import com.hotelbooking.Hotel_Booking_App.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    /**
     * Get all bookings.
     * @return List of all bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    /**
     * Get booking by ID.
     * @param bookingId ID of the booking.
     * @return Booking object wrapped in Optional.
     */
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepo.findById(bookingId);
    }

    /**
     * Create a new booking.
     * @param booking The booking object to be saved.
     * @return The created booking.
     */
    public Booking createBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    /**
     * Update an existing booking.
     * @param bookingId ID of the booking to update.
     * @param booking Updated booking data.
     * @return Updated booking object.
     */
    public Booking updateBooking(Long bookingId, Booking booking) {
        Optional<Booking> existingBooking = bookingRepo.findById(bookingId);

        if (existingBooking.isPresent()) {
            Booking updatedBooking = existingBooking.get();
            updatedBooking.setCheckIn(booking.getCheckIn());
            updatedBooking.setCheckOut(booking.getCheckOut());
            updatedBooking.setUser(booking.getUser());
            updatedBooking.setRoom(booking.getRoom());
            updatedBooking.setStatus(booking.getStatus());
            return bookingRepo.save(updatedBooking);
        } else {
            throw new RuntimeException("Booking not found");
        }
    }

    /**
     * Delete a booking by ID.
     * @param bookingId ID of the booking to delete.
     */
    public void deleteBooking(Long bookingId) {
        bookingRepo.deleteById(bookingId);
    }

    /**
     * Get bookings by hotel ID.
     * @param hotelId ID of the hotel.
     * @return List of bookings for the specified hotel.
     */
    public List<Booking> findBookingsByHotelId(Long hotelId) {
        return bookingRepo.findByRoom_Hotel_HotelId(hotelId);
    }
}
