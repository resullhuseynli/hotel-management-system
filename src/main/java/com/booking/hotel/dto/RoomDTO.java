package com.booking.hotel.dto;

import com.booking.hotel.enums.RoomStatus;
import com.booking.hotel.model.Hotel;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class RoomDTO {

    @Size(min = 1, max = 50) @NotNull
    private String roomNumber;
    @NotNull @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
    private RoomStatus status;
    private long hotelId;

}
