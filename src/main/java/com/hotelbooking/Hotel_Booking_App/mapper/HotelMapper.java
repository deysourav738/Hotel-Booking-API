package com.hotelbooking.Hotel_Booking_App.mapper;

import com.hotelbooking.Hotel_Booking_App.model.Hotel;
import com.hotelbooking.Hotel_Booking_App.response.HotelResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelMapper {

    public static HotelResponse toHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .hotelId(hotel.getHotelId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .rating(hotel.getRating())
                .amenities(hotel.getAmenities() != null
                        ? List.of(hotel.getAmenities().split(","))
                        : List.of())
                .roomTypes(hotel.getRooms().stream()
                        .map(room -> Map.<String, Object>of(
                                "roomType", room.getRoomType().toString(),
                                "price", room.getPricePerNight()
                        ))
                        .collect(Collectors.toList()))
                .photos(hotel.getPhotos())
                .build();
    }

    public static List<HotelResponse> toHotelResponse(List<Hotel> hotels) {
        return hotels.stream().map(HotelMapper::toHotelResponse).collect(Collectors.toList());
    }
}
