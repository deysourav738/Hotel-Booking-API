package com.hotelbooking.Hotel_Booking_App.controller;

import com.hotelbooking.Hotel_Booking_App.model.Hotel;
import com.hotelbooking.Hotel_Booking_App.model.User;
import com.hotelbooking.Hotel_Booking_App.service.AuthService;
import com.hotelbooking.Hotel_Booking_App.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> all() {
        List<User> users = userService.all();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get hotels by user ID
     */
    @GetMapping("/hotel")
    public ResponseEntity<Hotel> getHotelsByUser(HttpServletRequest request) {
        String token = authService.getToken(request);
        Optional<User> user = authService.getUserFromToken(token);
        if (user.isPresent()) {
            Hotel hotel = user.get().getHotel();
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
