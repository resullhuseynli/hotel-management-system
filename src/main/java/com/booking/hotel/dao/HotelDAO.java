package com.booking.hotel.dao;

import com.booking.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDAO extends JpaRepository<Hotel, Long> {
}
