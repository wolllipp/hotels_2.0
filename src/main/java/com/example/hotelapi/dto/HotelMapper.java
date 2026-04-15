package com.example.hotelapi.dto;

import com.example.hotelapi.entity.Address;
import com.example.hotelapi.entity.ArrivalTime;
import com.example.hotelapi.entity.Contacts;
import com.example.hotelapi.entity.Hotel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HotelMapper {

    public HotelShortDto toShortDto(Hotel hotel) {
        HotelShortDto dto = new HotelShortDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setAddress(hotel.getAddress() != null ? hotel.getAddress().toShortAddress() : null);
        dto.setPhone(hotel.getContacts() != null ? hotel.getContacts().getPhone() : null);
        return dto;
    }

    public HotelDetailDto toDetailDto(Hotel hotel) {
        HotelDetailDto dto = new HotelDetailDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setBrand(hotel.getBrand());
        dto.setAddress(toAddressDto(hotel.getAddress()));
        dto.setContacts(toContactsDto(hotel.getContacts()));
        dto.setArrivalTime(toArrivalTimeDto(hotel.getArrivalTime()));
        dto.setAmenities(hotel.getAmenities());
        return dto;
    }

    public Hotel toEntity(HotelCreateDto dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setDescription(dto.getDescription());
        hotel.setBrand(dto.getBrand());
        hotel.setAddress(toAddress(dto.getAddress()));
        hotel.setContacts(toContacts(dto.getContacts()));
        hotel.setArrivalTime(toArrivalTime(dto.getArrivalTime()));
        hotel.setAmenities(new ArrayList<>());
        return hotel;
    }

    private AddressDto toAddressDto(Address address) {
        if (address == null) return null;
        AddressDto dto = new AddressDto();
        dto.setHouseNumber(address.getHouseNumber());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setPostCode(address.getPostCode());
        return dto;
    }

    private ContactsDto toContactsDto(Contacts contacts) {
        if (contacts == null) return null;
        ContactsDto dto = new ContactsDto();
        dto.setPhone(contacts.getPhone());
        dto.setEmail(contacts.getEmail());
        return dto;
    }

    private ArrivalTimeDto toArrivalTimeDto(ArrivalTime arrivalTime) {
        if (arrivalTime == null) return null;
        ArrivalTimeDto dto = new ArrivalTimeDto();
        dto.setCheckIn(arrivalTime.getCheckIn());
        dto.setCheckOut(arrivalTime.getCheckOut());
        return dto;
    }

    private Address toAddress(AddressDto dto) {
        if (dto == null) return null;
        return new Address(dto.getHouseNumber(), dto.getStreet(), dto.getCity(), dto.getCountry(), dto.getPostCode());
    }

    private Contacts toContacts(ContactsDto dto) {
        if (dto == null) return null;
        return new Contacts(dto.getPhone(), dto.getEmail());
    }

    private ArrivalTime toArrivalTime(ArrivalTimeDto dto) {
        if (dto == null) return null;
        return new ArrivalTime(dto.getCheckIn(), dto.getCheckOut());
    }
}
