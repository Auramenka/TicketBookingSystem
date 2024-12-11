package com.innowise.ticketbookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {

    private Long id;
    private Long seanceId;
    private Long seatId;
    private Boolean isOccupied;
}
