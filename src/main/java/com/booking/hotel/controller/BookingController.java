package com.booking.hotel.controller;

import com.booking.hotel.dto.booking.BookingDtoReq;
import com.booking.hotel.dto.booking.BookingDtoRes;
import com.booking.hotel.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
@Validated
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingDtoRes> getBooking(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getBooking(id));
    }

    @PostMapping
    public ResponseEntity<BookingDtoRes> createBooking(@Valid @RequestBody BookingDtoReq bookingDtoReq) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.addBooking(bookingDtoReq));
    }

    @GetMapping
    public ResponseEntity<List<BookingDtoRes>> getAllBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
