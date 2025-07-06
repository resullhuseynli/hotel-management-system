package com.booking.hotel.dao.dto.hotel;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDtoRes {

    private Long id;
    private String name;
    private String location;
    private LocalDateTime createdAt;

}
