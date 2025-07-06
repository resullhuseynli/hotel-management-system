package com.booking.hotel.service;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dao.dto.room.RoomDtoReq;
import com.booking.hotel.dao.dto.room.RoomDtoRes;
import com.booking.hotel.dao.model.Hotel;
import com.booking.hotel.dao.model.Room;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomDAO roomDAO;
    private final HotelDAO hotelDAO;
    private final RoomMapper roomMapper;

    public RoomDtoRes addRoom(RoomDtoReq roomDtoReq) {
        return roomMapper.entityToDto(roomDAO.save(roomMapper.dtoToEntity(roomDtoReq)));
    }

    public List<RoomDtoRes> getAllRoomsByHotelId(Long hotelId) {
        hotelDAO.findById(hotelId)
                .orElseThrow(() -> new NotFoundException("Hotel with id: " + hotelId + " not found"));
        return roomDAO.getAllRoomsByHotelId(hotelId).orElseThrow().stream().map(roomMapper::entityToDto).toList();
    }

    public RoomDtoRes getRoomById(Long id) {
        Room room = roomDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with id: " + id + " not found"));
        return roomMapper.entityToDto(room);
    }

    public void deleteRoomById(Long id) {
        Room room = roomDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with id: " + id + " not found"));
        roomDAO.delete(room);
    }

    public RoomDtoRes updateRoom(RoomDtoReq roomDtoReq, Long id) {
        Room room = roomDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Room with id: " + id + " not found"));
        Hotel hotel = hotelDAO.findById(roomDtoReq.getHotelId())
                .orElseThrow(() -> new NotFoundException("Hotel with id: " + id + " not found"));
        room.setRoomNumber(roomDtoReq.getRoomNumber());
        room.setPrice(roomDtoReq.getPrice());
        room.setHotel(hotel);
        return roomMapper.entityToDto(roomDAO.save(room));
    }
}
