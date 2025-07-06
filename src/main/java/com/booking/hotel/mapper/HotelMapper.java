package com.booking.hotel.mapper;

import com.booking.hotel.dao.dto.hotel.HotelDtoReq;
import com.booking.hotel.dao.dto.hotel.HotelDtoRes;
import com.booking.hotel.dao.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelMapper {

    public HotelDtoRes entityToDto(Hotel hotel) {
        return HotelDtoRes.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .createdAt(hotel.getCreatedAt())
                .build();
    }

    public Hotel dtoToEntity(HotelDtoReq hotelDtoRes) {
        return Hotel.builder()
                .name(hotelDtoRes.getName())
                .location(hotelDtoRes.getLocation())
                .build();
    }
}
