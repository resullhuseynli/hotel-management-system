package com.booking.hotel.mapper;

import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dao.dto.booking.BookingDtoReq;
import com.booking.hotel.dao.dto.booking.BookingDtoRes;
import com.booking.hotel.dao.dto.room.RoomDtoRes;
import com.booking.hotel.dao.model.Booking;
import com.booking.hotel.dao.model.Room;
import com.booking.hotel.enums.BookingStatus;
import com.booking.hotel.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final RoomDAO roomDAO;
    private final RoomMapper roomMapper;

    public Booking dtoToEntity(BookingDtoReq bookingDtoReq) {
        Room room = roomDAO.findById(bookingDtoReq.getRoomId())
                .orElseThrow(() -> new NotFoundException("Room with id: " + bookingDtoReq.getRoomId() + " not found"));
        return Booking.builder()
                .status(BookingStatus.ACTIVE)
                .room(room)
                .endDate(bookingDtoReq.getEndDate())
                .startDate(bookingDtoReq.getStartDate())
                .costumerName(bookingDtoReq.getCostumerName())
                .costumerEmail(bookingDtoReq.getCostumerEmail())
                .build();
    }

    public BookingDtoRes entityToDto(Booking booking) {
        RoomDtoRes roomDtoRes = roomMapper.entityToDto(booking.getRoom());
        return BookingDtoRes.builder()
                .id(booking.getId())
                .createdAt(booking.getCreatedAt())
                .endDate(booking.getEndDate())
                .costumerEmail(booking.getCostumerEmail())
                .costumerName(booking.getCostumerName())
                .room(roomDtoRes)
                .status(booking.getStatus())
                .startDate(booking.getStartDate())
                .build();
    }
}
