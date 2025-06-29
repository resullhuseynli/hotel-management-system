package com.booking.hotel.service;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dto.HotelDTO;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelDAO hotelDAO;

    public Hotel addHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setLocation(hotelDTO.getLocation());
        return hotelDAO.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelDAO.findAll();
    }

    public Hotel getHotelById(long id) {
        Optional<Hotel> hotel = hotelDAO.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new NotFoundException("Hotel with id " + id + " not found");
        }
    }

    public Hotel updateHotel(HotelDTO hotelDTO, Long id) {
        Optional<Hotel> hotel = hotelDAO.findById(id);
        if (hotel.isPresent()) {
            hotel.get().setName(hotelDTO.getName());
            hotel.get().setLocation(hotelDTO.getLocation());
            return hotelDAO.save(hotel.get());
        } else {
            throw new NotFoundException("Hotel with id " + id + " not found");
        }
    }

    public void deleteHotel(Long id) {
        hotelDAO.deleteById(id);
    }
}
