package com.booking.hotel.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.badRequest().body(new ApiResponse(notFoundException.getMessage()));
    }
}
