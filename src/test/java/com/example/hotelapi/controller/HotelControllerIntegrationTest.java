package com.example.hotelapi.controller;

import com.example.hotelapi.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllHotels_shouldReturnList() throws Exception {
        mockMvc.perform(get("/property-view/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].address").exists())
                .andExpect(jsonPath("$[0].phone").exists());
    }

    @Test
    void getHotelById_shouldReturnDetailedInfo() throws Exception {
        mockMvc.perform(get("/property-view/hotels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("DoubleTree by Hilton Minsk"))
                .andExpect(jsonPath("$.brand").value("Hilton"))
                .andExpect(jsonPath("$.address.city").value("Minsk"))
                .andExpect(jsonPath("$.contacts.phone").exists())
                .andExpect(jsonPath("$.arrivalTime.checkIn").value("14:00"))
                .andExpect(jsonPath("$.amenities", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    void getHotelById_notFound_shouldReturn404() throws Exception {
        mockMvc.perform(get("/property-view/hotels/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createHotel_shouldReturnShortDto() throws Exception {
        HotelCreateDto dto = HotelCreateDto.builder()
                .name("Test Hotel")
                .description("A test hotel")
                .brand("TestBrand")
                .address(AddressDto.builder()
                        .houseNumber(10)
                        .street("Test Street")
                        .city("TestCity")
                        .country("TestCountry")
                        .postCode("12345")
                        .build())
                .contacts(ContactsDto.builder()
                        .phone("+1 234 567-89-00")
                        .email("test@test.com")
                        .build())
                .arrivalTime(ArrivalTimeDto.builder()
                        .checkIn("15:00")
                        .checkOut("11:00")
                        .build())
                .build();

        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Test Hotel"))
                .andExpect(jsonPath("$.address").value("10 Test Street, TestCity, 12345, TestCountry"))
                .andExpect(jsonPath("$.phone").value("+1 234 567-89-00"));
    }

    @Test
    void createHotel_invalidData_shouldReturn400() throws Exception {
        HotelCreateDto dto = HotelCreateDto.builder()
                .name("")
                .build();

        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addAmenities_shouldReturnUpdatedHotel() throws Exception {
        List<String> amenities = List.of("Pool", "Spa");

        mockMvc.perform(post("/property-view/hotels/1/amenities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(amenities)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amenities", hasItems("Pool", "Spa")));
    }

    @Test
    void searchHotels_byCity_shouldReturnFiltered() throws Exception {
        mockMvc.perform(get("/property-view/search")
                        .param("city", "Minsk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    void searchHotels_byBrand_shouldReturnFiltered() throws Exception {
        mockMvc.perform(get("/property-view/search")
                        .param("brand", "Hilton"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    void getHistogram_byCity_shouldReturnGrouped() throws Exception {
        mockMvc.perform(get("/property-view/histogram/city"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Minsk").exists());
    }

    @Test
    void getHistogram_byBrand_shouldReturnGrouped() throws Exception {
        mockMvc.perform(get("/property-view/histogram/brand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Hilton").exists());
    }

    @Test
    void getHistogram_byAmenities_shouldReturnGrouped() throws Exception {
        mockMvc.perform(get("/property-view/histogram/amenities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['Free WiFi']").exists());
    }

    @Test
    void getHistogram_invalidParam_shouldReturn400() throws Exception {
        mockMvc.perform(get("/property-view/histogram/invalid"))
                .andExpect(status().isBadRequest());
    }
}
