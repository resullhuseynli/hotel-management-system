package com.booking.hotel.service;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dto.room.RoomDtoReq;
import com.booking.hotel.dto.room.RoomDtoRes;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.model.Hotel;
import com.booking.hotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomDAO roomDAO;
    private final HotelDAO hotelDAO;

    public Room addRoom(RoomDtoReq roomDtoReq) {
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
        return roomDAO.save(room);
    }

    public List<Room> getAllRoomsByHotelId(Long hotelId) {
        Optional<List<Room>> rooms = roomDAO.getAllRoomsByHotelId(hotelId);
        if (rooms.isPresent()) {
            return  rooms.get();
        } else {
            throw new NotFoundException("Hotel with id " + hotelId + " not found");
        }
    }

    public Room getRoomById(long id) {
        Optional<Room> room = roomDAO.findById(id);
        if (room.isPresent()) {
            return room.get();
        }  else {
            throw new NotFoundException("Room with id " + id + " not found");
        }
    }

    public void deleteRoomById(Long id) {
        roomDAO.deleteById(id);
    }

    public Room updateRoom(RoomDtoReq roomDtoReq, long id) {
        Optional<Room> room = roomDAO.findById(id);
        if (room.isPresent()) {
            room.get().setRoomNumber(roomDtoReq.getRoomNumber());
            room.get().setPrice(roomDtoReq.getPrice());
            room.get().setStatus(roomDtoReq.getStatus());
            Optional<Hotel> hotel = hotelDAO.findById(roomDtoReq.getHotelId());
            if (hotel.isPresent()) {
                room.get().setHotel(hotel.get());
            } else {
                throw new NotFoundException("Hotel with id " + roomDtoReq.getHotelId() + " not found");
            }
            return  roomDAO.save(room.get());
        } else {
            throw new NotFoundException("Room with id " + id + " not found");
        }
    }
}
