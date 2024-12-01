package com.hotelbooking.Hotel_Booking_App.service;

import com.hotelbooking.Hotel_Booking_App.model.User;
import com.hotelbooking.Hotel_Booking_App.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> all(){
        return userRepo.findAll();
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    };
}
