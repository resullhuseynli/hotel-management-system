package com.booking.hotel.mapper;

import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dto.booking.BookingDtoReq;
import com.booking.hotel.dto.booking.BookingDtoRes;
import com.booking.hotel.dto.room.RoomDtoRes;
import com.booking.hotel.enums.RoomStatus;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.model.Booking;
import com.booking.hotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final RoomDAO roomDAO;
    private final RoomMapper roomMapper;

    public Booking BookingDtoToBooking(BookingDtoReq bookingDtoReq){
        Booking booking =  new Booking();
        booking.setCostumerName(bookingDtoReq.getCostumerName());
        booking.setCostumerEmail(bookingDtoReq.getCostumerEmail());
        booking.setStartDate(bookingDtoReq.getStartDate());
        booking.setEndDate(bookingDtoReq.getEndDate());
        booking.setStatus(bookingDtoReq.getStatus());
        Optional<Room> room = roomDAO.findById(bookingDtoReq.getRoomId());
        if(room.isPresent()){
            booking.setRoom(room.get());
            room.get().setStatus(RoomStatus.BOOKED);
            room.get().setBooking(booking);
            roomDAO.save(room.get());
        } else {
            throw new NotFoundException("Room with id: " + bookingDtoReq.getRoomId() + " not found");
        }
        return booking;
    }

    public BookingDtoRes  BookingToBookingDto(Booking booking){
        BookingDtoRes bookingDtoRes = new BookingDtoRes();
        bookingDtoRes.setId(booking.getId());
        bookingDtoRes.setCostumerName(booking.getCostumerName());
        bookingDtoRes.setCostumerEmail(booking.getCostumerEmail());
        bookingDtoRes.setStartDate(booking.getStartDate());
        bookingDtoRes.setEndDate(booking.getEndDate());
        bookingDtoRes.setStatus(booking.getStatus());
        bookingDtoRes.setCreatedAt(booking.getCreatedAt());
        RoomDtoRes  roomDtoRes = roomMapper.entityToDto(booking.getRoom());
        bookingDtoRes.setRoom(roomDtoRes);
        return bookingDtoRes;
    }
}
