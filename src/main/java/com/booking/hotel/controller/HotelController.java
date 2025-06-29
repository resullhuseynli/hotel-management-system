package com.booking.hotel.controller;

import com.booking.hotel.dto.hotel.HotelDtoReq;
import com.booking.hotel.dto.hotel.HotelDtoRes;
import com.booking.hotel.model.Hotel;
import com.booking.hotel.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/hotels")
@RequiredArgsConstructor
@Validated
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDtoRes> addHotel(@Valid @RequestBody HotelDtoReq hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
    }

    @GetMapping
    public ResponseEntity<List<HotelDtoRes>> getAllHotels() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDtoRes> getHotelById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> deleteHotelById(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDtoRes> updateHotel(@Valid @RequestBody HotelDtoReq hotelDtoReq, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.updateHotel(hotelDtoReq, id));
    }
}
