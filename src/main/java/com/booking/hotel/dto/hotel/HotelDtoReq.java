package com.booking.hotel.dto.hotel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDtoReq {

    @Size(min = 1, max = 255) @NotNull
    private String name;
    private String location;

}
