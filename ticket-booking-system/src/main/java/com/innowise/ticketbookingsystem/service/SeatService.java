package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.SeatDto;

import java.util.List;

public interface SeatService {

    List<SeatDto> getSeats();
}
