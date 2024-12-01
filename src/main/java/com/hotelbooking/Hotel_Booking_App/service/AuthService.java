package com.hotelbooking.Hotel_Booking_App.service;

import com.hotelbooking.Hotel_Booking_App.commons.Const;
import com.hotelbooking.Hotel_Booking_App.model.User;
import com.hotelbooking.Hotel_Booking_App.repo.UserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(Const.PASSWORD_ENCODER_STRENGTH);

    // Inject the domain value from application.properties
    @Value("${jwt.cookie.domain}")
    private String cookieDomain;

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String validate(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Bad credentials");
        }
        return jwtService.generateToken(user.getEmail());
    }

    // Helper method to set JWT token in HttpOnly cookie
    public void setJwtCookie(String token, HttpServletResponse response, int expTime) {
        // Create the HttpOnly cookie
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true); // Prevent JavaScript access
        cookie.setPath("/");
        cookie.setMaxAge(expTime);

        if (!cookieDomain.isEmpty() && !cookieDomain.contains("localhost")) {
            cookie.setSecure(true);
            cookie.setDomain(cookieDomain);
        } else {
            cookie.setSecure(false);
        }

        // Set SameSite flag for CSRF protection
        cookie.setAttribute("SameSite", "Strict");

        // Add cookie to the response
        response.addCookie(cookie);
    }
}
