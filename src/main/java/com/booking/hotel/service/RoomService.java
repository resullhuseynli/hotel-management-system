package com.booking.hotel.service;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dto.room.RoomDtoReq;
import com.booking.hotel.dto.room.RoomDtoRes;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.mapper.RoomMapper;
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
    private final RoomMapper roomMapper;

    public RoomDtoRes addRoom(RoomDtoReq roomDtoReq) {
        return roomMapper.entityToDto(roomDAO.save(roomMapper.dtoToEntity(roomDtoReq)));
    }

    public List<RoomDtoRes> getAllRoomsByHotelId(Long hotelId) {
        Optional<List<Room>> rooms = roomDAO.getAllRoomsByHotelId(hotelId);
        if (rooms.isPresent()) {
            List<RoomDtoRes> roomDtoResList = new ArrayList<>();
            for (Room room : rooms.get()) {
                RoomDtoRes roomDtoRes = roomMapper.entityToDto(room);
                roomDtoResList.add(roomDtoRes);
            }
            return roomDtoResList;
        } else {
            throw new NotFoundException("Hotel with id " + hotelId + " not found");
        }
    }

    public RoomDtoRes getRoomById(Long id) {
        Optional<Room> room = roomDAO.findById(id);
        if (room.isPresent()) {
            return roomMapper.entityToDto(room.get());
        } else {
            throw new NotFoundException("Room with id " + id + " not found");
        }
    }

    public void deleteRoomById(Long id) {
        Optional<Room> room = roomDAO.findById(id);
        if (room.isPresent()) {
            roomDAO.delete(room.get());
        } else {
            throw new NotFoundException("Room with id " + id + " not found");
        }
    }

    public RoomDtoRes updateRoom(RoomDtoReq roomDtoReq, Long id) {
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
            return roomMapper.entityToDto(roomDAO.save(room.get()));
        } else {
            throw new NotFoundException("Room with id " + id + " not found");
        }
    }
}
