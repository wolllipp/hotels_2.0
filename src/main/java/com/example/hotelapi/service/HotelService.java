package com.example.hotelapi.service;

import com.example.hotelapi.dto.*;

import java.util.List;
import java.util.Map;

public interface HotelService {

    List<HotelShortDto> getAllHotels();

    HotelDetailDto getHotelById(Long id);

    HotelShortDto createHotel(HotelCreateDto dto);

    HotelDetailDto addAmenities(Long id, List<String> amenities);

    List<HotelShortDto> searchHotels(String name, String brand, String city, String country, String amenities);

    Map<String, Long> getHistogram(String param);
}
