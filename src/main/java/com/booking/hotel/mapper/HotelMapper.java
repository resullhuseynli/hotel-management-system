package com.booking.hotel.mapper;

import com.booking.hotel.dto.hotel.HotelDtoReq;
import com.booking.hotel.dto.hotel.HotelDtoRes;
import com.booking.hotel.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotelMapper {

    public HotelDtoRes HotelToDtoRes(Hotel hotel) {
        HotelDtoRes hotelDtoRes = new HotelDtoRes();
        hotelDtoRes.setId(hotel.getId());
        hotelDtoRes.setName(hotel.getName());
        hotelDtoRes.setLocation(hotel.getLocation());
        hotelDtoRes.setCreatedAt(hotel.getCreatedAt());
        return hotelDtoRes;
    }

    public Hotel HotelToEntity(HotelDtoReq hotelDtoRes) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDtoRes.getName());
        hotel.setLocation(hotelDtoRes.getLocation());
        return hotel;
    }
}
