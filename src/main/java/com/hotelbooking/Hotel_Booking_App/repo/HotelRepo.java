package com.hotelbooking.Hotel_Booking_App.repo;

import com.hotelbooking.Hotel_Booking_App.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepo extends JpaRepository<Hotel,Long> {
    List<Hotel> findByLocation(String location);

    List<Hotel> findByRatingGreaterThanEqual(Double rating);
}
