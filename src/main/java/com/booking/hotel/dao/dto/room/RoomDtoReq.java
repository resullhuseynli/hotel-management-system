package com.booking.hotel.dao.dto.room;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoReq {

    @Size(min = 1, max = 50)
    @NotNull
    private String roomNumber;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
    private Long hotelId;

}
