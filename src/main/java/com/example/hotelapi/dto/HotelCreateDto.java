package com.example.hotelapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    public HotelCreateDto() {
    }

    public HotelCreateDto(String name, String description, String brand,
                          AddressDto address, ContactsDto contacts, ArrivalTimeDto arrivalTime) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.address = address;
        this.contacts = contacts;
        this.arrivalTime = arrivalTime;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public AddressDto getAddress() { return address; }
    public void setAddress(AddressDto address) { this.address = address; }

    public ContactsDto getContacts() { return contacts; }
    public void setContacts(ContactsDto contacts) { this.contacts = contacts; }

    public ArrivalTimeDto getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(ArrivalTimeDto arrivalTime) { this.arrivalTime = arrivalTime; }
}
