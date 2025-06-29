package com.booking.hotel.controller;

import com.booking.hotel.dto.RoomDTO;
import com.booking.hotel.model.Room;
import com.booking.hotel.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@Validated
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> addRoom(@Valid @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(roomDTO));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoomsByHotelId(@RequestParam long hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getAllRoomsByHotelId(hotelId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteRoomById(@PathVariable long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable long id, @Valid @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoom(roomDTO, id));
    }
}
