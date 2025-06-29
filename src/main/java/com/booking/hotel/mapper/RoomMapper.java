package com.booking.hotel.mapper;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dto.room.RoomDtoReq;
import com.booking.hotel.dto.room.RoomDtoRes;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.model.Hotel;
import com.booking.hotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoomMapper {

    private final HotelDAO hotelDAO;

    public Room dtoToEntity(RoomDtoReq roomDtoReq) {
        Room room = new Room();
        room.setRoomNumber(roomDtoReq.getRoomNumber());
        room.setPrice(roomDtoReq.getPrice());
        room.setStatus(roomDtoReq.getStatus());
        Optional<Hotel> hotel = hotelDAO.findById(roomDtoReq.getHotelId());
        if (hotel.isPresent()) {
            room.setHotel(hotel.get());
        } else {
            throw new NotFoundException("Hotel with id " + roomDtoReq.getHotelId() + " not found");
        }
        return room;
    }

    public RoomDtoRes entityToDto(Room room) {
        RoomDtoRes roomDtoRes = new RoomDtoRes();
        roomDtoRes.setId(room.getId());
        roomDtoRes.setRoomNumber(room.getRoomNumber());
        roomDtoRes.setPrice(room.getPrice());
        roomDtoRes.setStatus(room.getStatus());
        roomDtoRes.setHotelId(room.getHotel().getId());
        roomDtoRes.setCreatedDate(room.getCreatedAt());
        return roomDtoRes;
    }

}
