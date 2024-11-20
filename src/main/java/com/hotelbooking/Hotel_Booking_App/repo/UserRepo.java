package com.hotelbooking.Hotel_Booking_App.repo;

import com.hotelbooking.Hotel_Booking_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
