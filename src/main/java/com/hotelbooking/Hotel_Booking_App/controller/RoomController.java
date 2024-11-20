package com.hotelbooking.Hotel_Booking_App.controller;

import com.hotelbooking.Hotel_Booking_App.model.Room;
import com.hotelbooking.Hotel_Booking_App.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    /**
     * Get all rooms (Accessible to both Admin and User).
     */
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    /**
     * Get a room by ID (Accessible to both Admin and User).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long roomId) {
        Optional<Room> room = roomService.getRoomById(roomId);
        return room.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Add a new room (Accessible to Admin only).
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{hotelId}")
    public ResponseEntity<Room> addRoom(@PathVariable("hotelId") Long hotelId, @RequestBody Room room) {
        Room createdRoom = roomService.addRoomToHotel(hotelId, room);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    /**
     * Update an existing room (Accessible to Admin only).
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Long roomId, @RequestBody Room room) {
        Optional<Room> updatedRoom = Optional.ofNullable(roomService.updateRoom(roomId, room));
        return updatedRoom.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a room by ID (Accessible to Admin only).
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") Long roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Search rooms by availability (Accessible to both Admin and User).
     */
    @GetMapping("/search/availability")
    public ResponseEntity<List<Room>> searchRoomsByAvailability(@RequestParam("available") boolean isAvailable) {
        List<Room> rooms = roomService.searchRoomsByAvailability(isAvailable);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    /**
     * Search rooms by hotel ID (Accessible to both Admin and User).
     */
    @GetMapping("/search/hotel")
    public ResponseEntity<List<Room>> searchRoomsByHotelId(@RequestParam("hotelId") Long hotelId) {
        List<Room> rooms = roomService.searchRoomsByHotelId(hotelId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
