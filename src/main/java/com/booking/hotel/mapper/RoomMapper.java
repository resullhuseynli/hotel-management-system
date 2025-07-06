package com.booking.hotel.mapper;

import com.booking.hotel.dao.HotelDAO;
import com.booking.hotel.dao.dto.room.RoomDtoReq;
import com.booking.hotel.dao.dto.room.RoomDtoRes;
import com.booking.hotel.dao.model.Hotel;
import com.booking.hotel.dao.model.Room;
import com.booking.hotel.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.booking.hotel.enums.RoomStatus.AVAILABLE;

@Component
@RequiredArgsConstructor
public class RoomMapper {

    private final HotelDAO hotelDAO;
    private final HotelMapper hotelMapper;

    public Room dtoToEntity(RoomDtoReq roomDtoReq) {
        Hotel hotel = hotelDAO.findById(roomDtoReq.getHotelId())
                .orElseThrow(() -> new NotFoundException("Hotel with id:" + roomDtoReq.getHotelId() + " not found"));
        return Room.builder()
                .roomNumber(roomDtoReq.getRoomNumber())
                .price(roomDtoReq.getPrice())
                .status(AVAILABLE)
                .hotel(hotel)
                .build();
    }

    public RoomDtoRes entityToDto(Room room) {
        Hotel hotel = hotelDAO.findById(room.getHotel().getId())
                .orElseThrow(() -> new NotFoundException("Hotel with id:" + room.getHotel().getId() + " not found"));
        return RoomDtoRes.builder()
                .roomNumber(room.getRoomNumber())
                .price(room.getPrice())
                .status(room.getStatus())
                .createdAt(room.getCreatedAt())
                .id(room.getId())
                .hotel(hotelMapper.entityToDto(hotel))
                .build();
    }

}
