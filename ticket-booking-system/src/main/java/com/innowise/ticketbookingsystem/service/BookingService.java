package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;

import java.util.List;

public interface BookingService {
    void bookSeats(Seance seance, List<Long> seatIds);
    List<Seat> getAvailableSeats(Seance seance);
}
