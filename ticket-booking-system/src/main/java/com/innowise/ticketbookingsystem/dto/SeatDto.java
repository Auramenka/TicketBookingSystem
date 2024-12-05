package com.innowise.ticketbookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDto {

    private Long id;
    private Integer seatNumber;
    private Integer rowNumber;
    private Boolean isOccupied;
}
