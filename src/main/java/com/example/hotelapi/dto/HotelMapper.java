package com.example.hotelapi.dto;

import com.example.hotelapi.entity.Address;
import com.example.hotelapi.entity.ArrivalTime;
import com.example.hotelapi.entity.Contacts;
import com.example.hotelapi.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public HotelShortDto toShortDto(Hotel hotel) {
        return HotelShortDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .address(hotel.getAddress() != null ? hotel.getAddress().toShortAddress() : null)
                .phone(hotel.getContacts() != null ? hotel.getContacts().getPhone() : null)
                .build();
    }

    public HotelDetailDto toDetailDto(Hotel hotel) {
        return HotelDetailDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .brand(hotel.getBrand())
                .address(toAddressDto(hotel.getAddress()))
                .contacts(toContactsDto(hotel.getContacts()))
                .arrivalTime(toArrivalTimeDto(hotel.getArrivalTime()))
                .amenities(hotel.getAmenities())
                .build();
    }

    public Hotel toEntity(HotelCreateDto dto) {
        return Hotel.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .brand(dto.getBrand())
                .address(toAddress(dto.getAddress()))
                .contacts(toContacts(dto.getContacts()))
                .arrivalTime(toArrivalTime(dto.getArrivalTime()))
                .build();
    }

    private AddressDto toAddressDto(Address address) {
        if (address == null) return null;
        return AddressDto.builder()
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .postCode(address.getPostCode())
                .build();
    }

    private ContactsDto toContactsDto(Contacts contacts) {
        if (contacts == null) return null;
        return ContactsDto.builder()
                .phone(contacts.getPhone())
                .email(contacts.getEmail())
                .build();
    }

    private ArrivalTimeDto toArrivalTimeDto(ArrivalTime arrivalTime) {
        if (arrivalTime == null) return null;
        return ArrivalTimeDto.builder()
                .checkIn(arrivalTime.getCheckIn())
                .checkOut(arrivalTime.getCheckOut())
                .build();
    }

    private Address toAddress(AddressDto dto) {
        if (dto == null) return null;
        return Address.builder()
                .houseNumber(dto.getHouseNumber())
                .street(dto.getStreet())
                .city(dto.getCity())
                .country(dto.getCountry())
                .postCode(dto.getPostCode())
                .build();
    }

    private Contacts toContacts(ContactsDto dto) {
        if (dto == null) return null;
        return Contacts.builder()
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

    private ArrivalTime toArrivalTime(ArrivalTimeDto dto) {
        if (dto == null) return null;
        return ArrivalTime.builder()
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .build();
    }
}
