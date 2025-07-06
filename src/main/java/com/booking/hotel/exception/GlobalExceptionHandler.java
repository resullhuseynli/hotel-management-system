package com.booking.hotel.exception;

import com.booking.hotel.statics.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler<T> {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NotFoundException notFoundException) {
        log.error(ErrorCodes.NOT_FOUND_ERROR_CODE, notFoundException.getMessage());
        return ResponseEntity.badRequest().body(new ApiResponse<>(notFoundException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, List<String>>>> handleValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        log.error(ErrorCodes.VALIDATION_ERROR_CODE, errors);
        return new ResponseEntity<>(new ApiResponse<>(getErrorsMap(errors)), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomIsAlreadyBookedException.class)
    public ResponseEntity<ApiResponse<String>> handleRoomIsAlreadyBookedException(RoomIsAlreadyBookedException roomIsAlreadyBookedException) {
        log.error(ErrorCodes.ROOM_IS_ALREADY_BOOKED_ERROR_CODE, roomIsAlreadyBookedException.getMessage());
        return ResponseEntity.badRequest().body(new ApiResponse<>(roomIsAlreadyBookedException.getMessage()));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
