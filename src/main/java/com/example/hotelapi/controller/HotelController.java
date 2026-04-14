package com.example.hotelapi.controller;

import com.example.hotelapi.dto.*;
import com.example.hotelapi.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
@Tag(name = "Hotel API", description = "Operations for managing hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    @Operation(summary = "Get all hotels", description = "Returns a list of all hotels with short information")
    public ResponseEntity<List<HotelShortDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Get hotel by ID", description = "Returns detailed information about a specific hotel")
    public ResponseEntity<HotelDetailDto> getHotelById(
            @Parameter(description = "Hotel ID") @PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping("/hotels")
    @Operation(summary = "Create a new hotel", description = "Creates a new hotel and returns short information")
    public ResponseEntity<HotelShortDto> createHotel(@Valid @RequestBody HotelCreateDto dto) {
        HotelShortDto created = hotelService.createHotel(dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Add amenities to hotel", description = "Adds a list of amenities to the specified hotel")
    public ResponseEntity<HotelDetailDto> addAmenities(
            @Parameter(description = "Hotel ID") @PathVariable Long id,
            @RequestBody List<String> amenities) {
        return ResponseEntity.ok(hotelService.addAmenities(id, amenities));
    }

    @GetMapping("/search")
    @Operation(summary = "Search hotels", description = "Search hotels by name, brand, city, country, or amenities")
    public ResponseEntity<List<HotelShortDto>> searchHotels(
            @Parameter(description = "Hotel name") @RequestParam(required = false) String name,
            @Parameter(description = "Hotel brand") @RequestParam(required = false) String brand,
            @Parameter(description = "City") @RequestParam(required = false) String city,
            @Parameter(description = "Country") @RequestParam(required = false) String country,
            @Parameter(description = "Amenities") @RequestParam(required = false) String amenities) {
        return ResponseEntity.ok(hotelService.searchHotels(name, brand, city, country, amenities));
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Get histogram", description = "Returns hotel count grouped by specified parameter (brand, city, country, amenities)")
    public ResponseEntity<Map<String, Long>> getHistogram(
            @Parameter(description = "Parameter to group by: brand, city, country, amenities")
            @PathVariable String param) {
        return ResponseEntity.ok(hotelService.getHistogram(param));
    }
}
