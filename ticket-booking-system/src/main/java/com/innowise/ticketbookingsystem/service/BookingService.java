package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.BookingDto;
import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.dto.SeatDto;

import java.util.List;

public interface BookingService {
    BookingDto bookSeats(SeanceDto seanceDto, List<Long> seatIds);
    List<SeatDto> getAvailableSeats(SeanceDto seanceDto);
}
