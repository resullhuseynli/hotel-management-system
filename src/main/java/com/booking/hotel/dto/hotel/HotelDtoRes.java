package com.booking.hotel.dto.hotel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class HotelDtoRes {

    private Long id;
    private String name;
    private String location;
    private LocalDateTime createdAt;

}
