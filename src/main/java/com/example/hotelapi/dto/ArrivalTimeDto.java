package com.example.hotelapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalTimeDto {

    private String checkIn;
    private String checkOut;
}
