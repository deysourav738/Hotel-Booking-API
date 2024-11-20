package com.hotelbooking.Hotel_Booking_App.service;

import com.hotelbooking.Hotel_Booking_App.model.Hotel;
import com.hotelbooking.Hotel_Booking_App.model.Room;
import com.hotelbooking.Hotel_Booking_App.repo.HotelRepo;
import com.hotelbooking.Hotel_Booking_App.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    HotelRepo hotelRepo;

    @Autowired
    RoomRepo roomRepo;

    /**
     * Get all rooms.
     * @return List of all rooms.
     */
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    /**
     * Get a room by its ID.
     * @param roomId ID of the room.
     * @return Optional of Room.
     */
    public Optional<Room> getRoomById(Long roomId) {
        return roomRepo.findById(roomId);
    }

    /**
     * Add a new room to a hotel.
     * @param room The room object to be added.
     * @return The room object after being saved.
     */
    public Room addRoomToHotel(Long hotelId, Room room) {
        // Find the hotel by its ID
        Optional<Hotel> hotelOptional = hotelRepo.findById(hotelId);

        if (hotelOptional.isEmpty()) {
            throw new RuntimeException("Hotel not found for the given hotelId: " + hotelId);
        }

        // Get the hotel from the Optional
        Hotel hotel = hotelOptional.get();

        // Set the hotel to the room
        room.setHotel(hotel);

        // Save the room and return the created room
        return roomRepo.save(room);
    }

    /**
     * Update an existing room.
     * @param roomId ID of the room to update.
     * @param room Updated Room details.
     * @return Updated Room object.
     */
    public Room updateRoom(Long roomId, Room room) {
        Optional<Room> existingRoom = roomRepo.findById(roomId);

        if (existingRoom.isPresent()) {
            Room updatedRoom = existingRoom.get();
            updatedRoom.setRoomType(room.getRoomType());
            updatedRoom.setPricePerNight(room.getPricePerNight());
            updatedRoom.setAvailability(room.getAvailability());
            updatedRoom.setHotel(room.getHotel());
            return roomRepo.save(updatedRoom);
        } else {
            throw new RuntimeException("Room not found");
        }
    }

    /**
     * Delete a room by its ID.
     * @param roomId ID of the room to delete.
     */
    public void deleteRoom(Long roomId) {
        roomRepo.deleteById(roomId);
    }

    /**
     * Search rooms by availability.
     * @param isAvailable Room availability status.
     * @return List of rooms matching the criteria.
     */
    public List<Room> searchRoomsByAvailability(boolean isAvailable) {
        return roomRepo.findByAvailability(isAvailable);
    }

    /**
     * Search rooms by hotel ID.
     * @param hotelId ID of the hotel.
     * @return List of rooms in the specified hotel.
     */
    public List<Room> searchRoomsByHotelId(Long hotelId) {
        return roomRepo.findByHotel_HotelId(hotelId);
    }
}
