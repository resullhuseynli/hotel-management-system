package com.booking.hotel.controller;

import com.booking.hotel.dto.HotelDTO;
import com.booking.hotel.model.Hotel;
import com.booking.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody HotelDTO hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> deleteHotelById(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody HotelDTO hotelDTO, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.updateHotel(hotelDTO, id));
    }
}
