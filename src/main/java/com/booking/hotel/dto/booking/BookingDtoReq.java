package com.booking.hotel.dto.booking;

import com.booking.hotel.enums.BookingStatus;
import com.booking.hotel.model.Room;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoReq {

    @Size(min = 1, max = 255) @NotNull
    private String costumerName;
    @Email @NotNull
    private String costumerEmail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private Long roomId;

}
