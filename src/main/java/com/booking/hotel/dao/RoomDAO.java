package com.booking.hotel.dao;

import com.booking.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomDAO extends JpaRepository<Room, Long> {
    Optional<List<Room>> getAllRoomsByHotelId(long hotelId);
}
