package com.booking.hotel.dao;

import com.booking.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDAO extends JpaRepository<Room, Long> {
}
