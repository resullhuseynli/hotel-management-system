package com.booking.hotel.controller;

import com.booking.hotel.dto.room.RoomDtoReq;
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
    public ResponseEntity<Room> addRoom(@Valid @RequestBody RoomDtoReq roomDtoReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(roomDtoReq));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoomsByHotelId(@RequestParam Long hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getAllRoomsByHotelId(hotelId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDtoReq roomDtoReq) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoom(roomDtoReq, id));
    }
}
