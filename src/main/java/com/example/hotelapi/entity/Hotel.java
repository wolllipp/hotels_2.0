package com.example.hotelapi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq")
    @SequenceGenerator(name = "hotel_seq", sequenceName = "hotel_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String brand;

    @Embedded
    private Address address;

    @Embedded
    private Contacts contacts;

    @Embedded
    private ArrivalTime arrivalTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<String> amenities = new ArrayList<>();

    public Hotel() {
    }

    public Hotel(Long id, String name, String description, String brand,
                 Address address, Contacts contacts, ArrivalTime arrivalTime, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.address = address;
        this.contacts = contacts;
        this.arrivalTime = arrivalTime;
        this.amenities = amenities != null ? amenities : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Contacts getContacts() { return contacts; }
    public void setContacts(Contacts contacts) { this.contacts = contacts; }

    public ArrivalTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(ArrivalTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
