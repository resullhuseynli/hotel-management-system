package com.booking.hotel.dto.room;

import com.booking.hotel.enums.RoomStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoRes {

    private Long id;
    private String roomNumber;
    private BigDecimal price;
    private RoomStatus status;
    private LocalDateTime createdDate;
    private Long hotelId;

}
