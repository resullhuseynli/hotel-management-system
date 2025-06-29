package com.booking.hotel.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {

    private String message;
    private final LocalDateTime timestamp =  LocalDateTime.now();

    protected ApiResponse(String message) {
        this.message = message;
    }

}
