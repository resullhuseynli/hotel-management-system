package com.booking.hotel.dao;

import com.booking.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Long> {
}
