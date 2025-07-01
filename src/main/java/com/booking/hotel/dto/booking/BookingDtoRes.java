package com.booking.hotel.dto.booking;

import com.booking.hotel.dto.room.RoomDtoRes;
import com.booking.hotel.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoRes {

    private Long id;
    private String costumerName;
    private String costumerEmail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private LocalDateTime createdAt;
    private RoomDtoRes room;

}
