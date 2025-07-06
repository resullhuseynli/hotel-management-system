package com.booking.hotel.dao.dto.room;

import com.booking.hotel.dao.dto.hotel.HotelDtoRes;
import com.booking.hotel.enums.RoomStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDtoRes {

    private Long id;
    private String roomNumber;
    private BigDecimal price;
    private RoomStatus status;
    private LocalDateTime createdAt;
    private HotelDtoRes hotel;

}
