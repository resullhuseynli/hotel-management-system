package com.booking.hotel.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T message;
    private final LocalDateTime timestamp =  LocalDateTime.now();

    protected ApiResponse(T message) {
        this.message = message;
    }

}
