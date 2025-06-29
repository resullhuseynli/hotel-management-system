package com.booking.hotel.dto.room;

import com.booking.hotel.enums.RoomStatus;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoReq {

    @Size(min = 1, max = 50) @NotNull
    private String roomNumber;
    @NotNull @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
    private RoomStatus status;
    private Long hotelId;

}
