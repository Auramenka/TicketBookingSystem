package com.innowise.ticketbookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SeanceDto {

    private Long id;
    private LocalDate dateStart;
    private LocalTime timeStart;
    private Long eventId;
}
