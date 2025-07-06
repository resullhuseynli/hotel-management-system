package com.booking.hotel.service;

import com.booking.hotel.dao.BookingDAO;
import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dao.dto.booking.BookingDtoReq;
import com.booking.hotel.dao.dto.booking.BookingDtoRes;
import com.booking.hotel.dao.model.Booking;
import com.booking.hotel.dao.model.Room;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.exception.RoomIsAlreadyBookedException;
import com.booking.hotel.mapper.BookingMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.booking.hotel.enums.RoomStatus.AVAILABLE;
import static com.booking.hotel.enums.RoomStatus.BOOKED;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingDAO bookingDAO;
    private final RoomDAO roomDAO;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDtoRes createBooking(BookingDtoReq bookingDtoReq) {
        Booking booking = bookingMapper.dtoToEntity(bookingDtoReq);
        Room room = roomDAO.findById(bookingDtoReq.getRoomId()).orElseThrow(() -> new NotFoundException("Room with id: " + bookingDtoReq.getRoomId() + " not found"));
        if (room.getStatus() == BOOKED)
            throw new RoomIsAlreadyBookedException("Room with id: " + room.getId() + " already booked");
        room.setStatus(BOOKED);
        roomDAO.save(room);
        bookingDAO.save(booking);
        return bookingMapper.entityToDto(booking);
    }

    public List<BookingDtoRes> getAllBookings() {
        return bookingDAO.findAll().stream().map(bookingMapper::entityToDto).collect(Collectors.toList());
    }

    public BookingDtoRes getBooking(Long id) {
        Booking booking = bookingDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id: " + id + " not found"));
        return bookingMapper.entityToDto(booking);
    }

    @Transactional
    public void deleteBooking(Long id) {
        Booking booking = bookingDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id: " + id + " not found"));
        Room room = roomDAO.findById(booking.getRoom().getId())
                .orElseThrow(() -> new NotFoundException("Room with id: " + booking.getRoom().getId() + " not found"));
        bookingDAO.delete(booking);
        room.setStatus(AVAILABLE);
        room.setBooking(null);
        roomDAO.save(room);
    }
}
