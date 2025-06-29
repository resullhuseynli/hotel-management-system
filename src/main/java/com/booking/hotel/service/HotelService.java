package com.booking.hotel.service;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dto.hotel.HotelDtoReq;
import com.booking.hotel.dto.hotel.HotelDtoRes;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.mapper.HotelMapper;
import com.booking.hotel.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelDAO hotelDAO;
    private final HotelMapper hotelMapper;

    public HotelDtoRes addHotel(HotelDtoReq hotelDtoReq) {
        Hotel hotel = hotelMapper.HotelToEntity(hotelDtoReq);
        hotelDAO.save(hotel);
        return hotelMapper.HotelToDtoRes(hotel);
    }

    public List<HotelDtoRes> getAllHotels() {
        List<Hotel> hotels = hotelDAO.findAll();
        List<HotelDtoRes> hotelDtoRes = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDtoRes.add(hotelMapper.HotelToDtoRes(hotel));
        }
        return hotelDtoRes;
    }

    public HotelDtoRes getHotelById(long id) {
        Optional<Hotel> hotel = hotelDAO.findById(id);
        if (hotel.isPresent()) {
            return hotelMapper.HotelToDtoRes(hotel.get());
        } else {
            throw new NotFoundException("Hotel with id " + id + " not found");
        }
    }

    public HotelDtoRes updateHotel(HotelDtoReq hotelDtoReq, Long id) {
        Optional<Hotel> hotelOptional = hotelDAO.findById(id);
        if (hotelOptional.isPresent()) {
            hotelOptional.get().setName(hotelDtoReq.getName());
            hotelOptional.get().setLocation(hotelDtoReq.getLocation());
            hotelDAO.save(hotelOptional.get());
            return hotelMapper.HotelToDtoRes(hotelOptional.get());
        } else {
            throw new NotFoundException("Hotel with id " + id + " not found");
        }
    }

    public void deleteHotel(Long id) {
        hotelDAO.deleteById(id);
    }
}
