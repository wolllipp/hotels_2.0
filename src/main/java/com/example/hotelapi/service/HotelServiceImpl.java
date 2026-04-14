package com.example.hotelapi.service;

import com.example.hotelapi.dto.*;
import com.example.hotelapi.entity.Hotel;
import com.example.hotelapi.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelShortDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDetailDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id: " + id));
        return hotelMapper.toDetailDto(hotel);
    }

    @Override
    @Transactional
    public HotelShortDto createHotel(HotelCreateDto dto) {
        Hotel hotel = hotelMapper.toEntity(dto);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toShortDto(saved);
    }

    @Override
    @Transactional
    public HotelDetailDto addAmenities(Long id, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id: " + id));

        for (String amenity : amenities) {
            if (!hotel.getAmenities().contains(amenity)) {
                hotel.getAmenities().add(amenity);
            }
        }

        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toDetailDto(saved);
    }

    @Override
    public List<HotelShortDto> searchHotels(String name, String brand, String city, String country, String amenities) {
        return hotelRepository.search(name, brand, city, country, amenities).stream()
                .distinct()
                .map(hotelMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getHistogram(String param) {
        List<Object[]> results = switch (param.toLowerCase()) {
            case "brand" -> hotelRepository.countByBrand();
            case "city" -> hotelRepository.countByCity();
            case "country" -> hotelRepository.countByCountry();
            case "amenities" -> hotelRepository.countByAmenities();
            default -> throw new IllegalArgumentException(
                    "Invalid histogram parameter: " + param + ". Allowed values: brand, city, country, amenities");
        };

        return results.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1],
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
