package com.hotelbooking.Hotel_Booking_App.service;

import com.hotelbooking.Hotel_Booking_App.model.Hotel;
import com.hotelbooking.Hotel_Booking_App.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepo hotelRepo;

    /**
     * Get all hotels.
     * @return List of hotels.
     */
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    /**
     * Get a hotel by ID.
     * @param hotelId the ID of the hotel.
     * @return Optional of Hotel.
     */
    public Optional<Hotel> getHotelById(Long hotelId) {
        return hotelRepo.findById(hotelId);
    }

    /**
     * Add a new hotel.
     * @param hotel the hotel to be added.
     * @return the saved hotel.
     */
    public Hotel addHotel(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    /**
     * Update an existing hotel.
     * @param hotelId the ID of the hotel to update.
     * @param hotel the hotel data to update.
     * @return the updated hotel.
     */
    public Hotel updateHotel(Long hotelId, Hotel hotel) {
        Optional<Hotel> existingHotel = hotelRepo.findById(hotelId);

        if(existingHotel.isPresent()) {
            Hotel updatedHotel = existingHotel.get();
            updatedHotel.setName(hotel.getName());
            updatedHotel.setLocation(hotel.getLocation());
            updatedHotel.setDescription(hotel.getDescription());
            updatedHotel.setRating(hotel.getRating());
            updatedHotel.setAmenities(hotel.getAmenities());
            return hotelRepo.save(updatedHotel);
        } else {
            throw new RuntimeException("Hotel not found");
        }
    }

    /**
     * Delete a hotel by ID.
     * @param hotelId the ID of the hotel to delete.
     */
    public void deleteHotel(Long hotelId) {
        hotelRepo.deleteById(hotelId);
    }

    /**
     * Search hotels by location.
     * @param location the location to search for.
     * @return List of hotels in the specified location.
     */
    public List<Hotel> searchHotelsByLocation(String location) {
        return hotelRepo.findByLocation(location);
    }

    /**
     * Search hotels by rating.
     * @param rating the rating to search for.
     * @return List of hotels with the specified rating.
     */
    public List<Hotel> searchHotelsByRating(Double rating) {
        return hotelRepo.findByRatingGreaterThanEqual(rating);
    }
}
