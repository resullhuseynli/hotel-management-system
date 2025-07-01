package com.booking.hotel.service;

import com.booking.hotel.dao.BookingDAO;
import com.booking.hotel.dao.RoomDAO;
import com.booking.hotel.dto.booking.BookingDtoReq;
import com.booking.hotel.dto.booking.BookingDtoRes;
import com.booking.hotel.enums.RoomStatus;
import com.booking.hotel.exception.NotFoundException;
import com.booking.hotel.mapper.BookingMapper;
import com.booking.hotel.model.Booking;
import com.booking.hotel.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingDAO  bookingDAO;
    private final RoomDAO roomDAO;
    private final BookingMapper bookingMapper;

    public BookingDtoRes addBooking(BookingDtoReq bookingDtoReq) {
        Booking booking = bookingMapper.BookingDtoToBooking(bookingDtoReq);
        bookingDAO.save(booking);
        return bookingMapper.BookingToBookingDto(booking);
    }

    public List<BookingDtoRes> getAllBookings() {
        List<Booking> bookings = bookingDAO.findAll();
        List<BookingDtoRes> bookingDtoResList = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDtoRes bookingDtoRes = bookingMapper.BookingToBookingDto(booking);
            bookingDtoResList.add(bookingDtoRes);
        }
        return bookingDtoResList;
    }

    public BookingDtoRes getBooking(Long id) {
        Optional<Booking> booking = bookingDAO.findById(id);
        if (booking.isPresent()) {
            return bookingMapper.BookingToBookingDto(booking.get());
        }  else {
            throw new NotFoundException("Booking with id: " + id + " not found");
        }
    }

    public void deleteBooking(Long id) {
        Optional<Booking> booking = bookingDAO.findById(id);
        if (booking.isPresent()) {
            Room room = roomDAO.findById(booking.get().getRoom().getId())
                    .orElseThrow(() -> new NotFoundException("Room with id: " + booking.get().getRoom().getId() + " not found"));
            room.setStatus(RoomStatus.AVAILABLE);
            room.setBooking(null);
            roomDAO.save(room);
            bookingDAO.delete(booking.get());
        }   else {
            throw new NotFoundException("Booking with id: " + id + " not found");
        }
    }
}
