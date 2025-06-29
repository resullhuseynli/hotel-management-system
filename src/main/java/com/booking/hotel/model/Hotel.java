package com.booking.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Hotel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 1, max = 255) @NotNull
    private String name;
    private String location;
    private final LocalDateTime createdAt = LocalDateTime.now();
}
