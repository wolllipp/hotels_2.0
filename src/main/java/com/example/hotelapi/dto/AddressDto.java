package com.example.hotelapi.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressDto {

    private Integer houseNumber;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Country is required")
    private String country;

    private String postCode;

    public AddressDto() {
    }

    public AddressDto(Integer houseNumber, String street, String city, String country, String postCode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    public Integer getHouseNumber() { return houseNumber; }
    public void setHouseNumber(Integer houseNumber) { this.houseNumber = houseNumber; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPostCode() { return postCode; }
    public void setPostCode(String postCode) { this.postCode = postCode; }
}
