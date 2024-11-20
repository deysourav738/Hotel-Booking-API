package com.hotelbooking.Hotel_Booking_App.repo;

import com.hotelbooking.Hotel_Booking_App.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    // Custom query to find bookings by a user's userId
    List<Booking> findByUser_UserId(Long userId);

    // Custom query to find bookings by room's roomId
    List<Booking> findByRoom_RoomId(Long roomId);

    // Custom query to find bookings by booking status
    List<Booking> findByStatus(String status);

    // Custom query to find bookings by both userId and roomId
    Optional<Booking> findByUser_UserIdAndRoom_RoomId(Long userId, Long roomId);

    // Custom query to find bookings by hotelId (through the Room entity)
    List<Booking> findByRoom_Hotel_HotelId(Long hotelId);

}
