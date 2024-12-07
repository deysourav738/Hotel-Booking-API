package com.hotelbooking.Hotel_Booking_App.response;

import com.hotelbooking.Hotel_Booking_App.model.HotelPhoto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class HotelResponse {
    private Long hotelId;
    private String name;
    private String location;
    private String description;
    private Double rating;
    private List<String> amenities;
    private List<Map<String, Object>> roomTypes;
    private List<HotelPhoto> photos;
}
