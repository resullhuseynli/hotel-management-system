package com.booking.hotel.service;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dao.dto.hotel.HotelDtoReq;
import com.booking.hotel.dao.dto.hotel.HotelDtoRes;
import com.booking.hotel.dao.model.Hotel;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelDAO hotelDAO;
    private final HotelMapper hotelMapper;

    public HotelDtoRes createHotel(HotelDtoReq hotelDtoReq) {
        Hotel hotel = hotelMapper.dtoToEntity(hotelDtoReq);
        hotelDAO.save(hotel);
        return hotelMapper.entityToDto(hotel);
    }

    public List<HotelDtoRes> getAllHotels() {
        List<Hotel> hotels = hotelDAO.findAll();
        return hotels.stream().map(hotelMapper::entityToDto).collect(Collectors.toList());
    }

    public HotelDtoRes getHotelById(Long id) {
        Hotel hotel = hotelDAO.findById(id).orElseThrow(() -> new NotFoundException("Hotel with id:" + id + " not found"));
        return hotelMapper.entityToDto(hotel);
    }

    public HotelDtoRes updateHotel(HotelDtoReq hotelDtoReq, Long id) {
        Hotel hotel = hotelDAO.findById(id).orElseThrow(() -> new NotFoundException("Hotel with id:" + id + " not found"));
        hotel.setName(hotelDtoReq.getName());
        hotel.setLocation(hotelDtoReq.getLocation());
        hotelDAO.save(hotel);
        return hotelMapper.entityToDto(hotel);
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelDAO.findById(id).orElseThrow(() -> new NotFoundException("Hotel with id:" + id + " not found"));
        hotelDAO.delete(hotel);
    }
}
