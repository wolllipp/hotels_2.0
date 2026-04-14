package com.example.hotelapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelCreateDto {

    @NotBlank(message = "Hotel name is required")
    private String name;

    private String description;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Address is required")
    @Valid
    private AddressDto address;

    @NotNull(message = "Contacts are required")
    @Valid
    private ContactsDto contacts;

    @NotNull(message = "Arrival time is required")
    @Valid
    private ArrivalTimeDto arrivalTime;
}
