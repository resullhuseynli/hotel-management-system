package com.booking.hotel.model;

import com.booking.hotel.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 50) @NotNull
    private String roomNumber;
    @NotNull @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
    private RoomStatus status;
    private final LocalDateTime createdAt =  LocalDateTime.now();
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Hotel hotel;
}
