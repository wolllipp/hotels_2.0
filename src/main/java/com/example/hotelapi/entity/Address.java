package com.example.hotelapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Column(name = "house_number")
    private Integer houseNumber;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(name = "post_code")
    private String postCode;

    public String toShortAddress() {
        StringBuilder sb = new StringBuilder();
        if (houseNumber != null) {
            sb.append(houseNumber).append(" ");
        }
        sb.append(street).append(", ");
        sb.append(city).append(", ");
        if (postCode != null && !postCode.isEmpty()) {
            sb.append(postCode).append(", ");
        }
        sb.append(country);
        return sb.toString();
    }
}
