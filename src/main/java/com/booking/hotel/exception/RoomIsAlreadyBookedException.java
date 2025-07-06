package com.booking.hotel.exception;

public class RoomIsAlreadyBookedException extends RuntimeException {
    public RoomIsAlreadyBookedException(String message) {
        super(message);
    }
}
