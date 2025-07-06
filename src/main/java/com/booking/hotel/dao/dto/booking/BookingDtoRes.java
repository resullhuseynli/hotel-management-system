package com.booking.hotel.dao.dto.booking;

import com.booking.hotel.dao.dto.room.RoomDtoRes;
import com.booking.hotel.enums.BookingStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
