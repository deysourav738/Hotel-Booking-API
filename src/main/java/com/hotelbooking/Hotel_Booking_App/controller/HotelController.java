package com.hotelbooking.Hotel_Booking_App.controller;

import com.hotelbooking.Hotel_Booking_App.mapper.HotelMapper;
import com.hotelbooking.Hotel_Booking_App.model.Hotel;
import com.hotelbooking.Hotel_Booking_App.response.HotelResponse;
import com.hotelbooking.Hotel_Booking_App.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;


    /**
     * Get all hotels (Accessible to both Admin and User)
     */
    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(HotelMapper.toHotelResponse(hotels), HttpStatus.OK);
    }

    /**
     * Get hotel by ID (Accessible to both Admin and User)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Long hotelId) {
        Optional<Hotel> hotel = hotelService.getHotelById(hotelId);
        return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Add a new hotel (Accessible to Admin only)
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.addHotel(hotel);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    /**
     * Update an existing hotel (Accessible to Admin only)
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable("id") Long hotelId, @RequestBody Hotel hotel) {
        Optional<Hotel> updatedHotel = Optional.ofNullable(hotelService.updateHotel(hotelId, hotel));
        return updatedHotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a hotel by ID (Accessible to Admin only)
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") Long hotelId) {
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Search hotels by location (Accessible to both Admin and User)
     */
    @GetMapping("/search/location")
    public ResponseEntity<List<Hotel>> searchHotelsByLocation(@RequestParam("location") String location) {
        List<Hotel> hotels = hotelService.searchHotelsByLocation(location);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    /**
     * Search hotels by rating (Accessible to both Admin and User)
     */
    @GetMapping("/search/rating")
    public ResponseEntity<List<Hotel>> searchHotelsByRating(@RequestParam("rating") Double rating) {
        List<Hotel> hotels = hotelService.searchHotelsByRating(rating);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

}
