package com.booking.hotel.model;

import com.booking.hotel.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 255) @NotNull
    private String costumerName;
    @Email @NotNull
    private String costumerEmail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private final LocalDateTime createdAt = LocalDateTime.now();
    @OneToOne
    private Room room;
}
