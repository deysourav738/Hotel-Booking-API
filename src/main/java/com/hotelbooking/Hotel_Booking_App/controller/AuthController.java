package com.hotelbooking.Hotel_Booking_App.controller;

import com.hotelbooking.Hotel_Booking_App.commons.UserRole;
import com.hotelbooking.Hotel_Booking_App.model.User;
import com.hotelbooking.Hotel_Booking_App.response.UserResponse;
import com.hotelbooking.Hotel_Booking_App.service.AuthService;
import com.hotelbooking.Hotel_Booking_App.service.JWTService;
import com.hotelbooking.Hotel_Booking_App.service.UserDetailsServiceImpl;
import com.hotelbooking.Hotel_Booking_App.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody User user) {
        try {
            if (user.getRole() == null) {
                user.setRole(UserRole.USER);
            }
            authService.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new UserResponse(user.getName(), user.getEmail()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody User user, HttpServletResponse response) {
        try {
            String token = authService.validate(user);
            authService.setJwtCookie(token, response, 1800);
            Optional<User> foundUser = userService.findByEmail(user.getEmail());
            UserResponse userResponse = new UserResponse();
            if (foundUser.isPresent()) {
                userResponse.setName(foundUser.get().getName());
                userResponse.setEmail(foundUser.get().getEmail());
            }
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUser(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null || jwtService.isTokenExpired(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Unauthorized if token is missing or expired
        }

        // Extract user details from the token
        String username = jwtService.extractUserName(token);
        Optional<User> user = userService.findByEmail(username);
        UserResponse userResponse = new UserResponse();
        if (user.isPresent()) {
            userResponse.setName(user.get().getName());
            userResponse.setEmail(user.get().getEmail());
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        authService.setJwtCookie(null, response, 0);
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }


}
