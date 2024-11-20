package com.hotelbooking.Hotel_Booking_App.repo;

import com.hotelbooking.Hotel_Booking_App.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room,Long> {
    // Find rooms by availability status
    List<Room> findByAvailability(Boolean availability);

    // Find rooms by hotel ID
    List<Room> findByHotel_HotelId(Long hotelId);

    // Find rooms by room type
    List<Room> findByRoomType(String roomType);

}
