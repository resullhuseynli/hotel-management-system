package com.booking.hotel.service;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dto.RoomDTO;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.model.Hotel;
import com.booking.hotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomDAO roomDAO;
    private final HotelDAO hotelDAO;

    public Room addRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setPrice(roomDTO.getPrice());
        room.setStatus(roomDTO.getStatus());
        Optional<Hotel> hotel = hotelDAO.findById(roomDTO.getHotelId());
        if (hotel.isPresent()) {
            room.setHotel(hotel.get());
        } else {
            throw new NotFoundException("Hotel with id " + roomDTO.getHotelId() + " not found");
        }
        return roomDAO.save(room);
    }

    public List<Room> getAllRoomsByHotelId(long hotelId) {
        Optional<List<Room>> rooms = roomDAO.getAllRoomsByHotelId(hotelId);
        if (rooms.isPresent()) {
            return rooms.get();
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

    public void deleteRoomById(long id) {
        roomDAO.deleteById(id);
    }

    public Room updateRoom(RoomDTO roomDTO,  long id) {
        Optional<Room> room = roomDAO.findById(id);
        if (room.isPresent()) {
            room.get().setRoomNumber(roomDTO.getRoomNumber());
            room.get().setPrice(roomDTO.getPrice());
            room.get().setStatus(roomDTO.getStatus());
            Optional<Hotel> hotel = hotelDAO.findById(roomDTO.getHotelId());
            if (hotel.isPresent()) {
                room.get().setHotel(hotel.get());
            } else {
                throw new NotFoundException("Hotel with id " + roomDTO.getHotelId() + " not found");
            }
            return  roomDAO.save(room.get());
        } else {
            throw new NotFoundException("Room with id " + id + " not found");
        }
    }
}
