package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.model.Seat;

import java.util.List;

public interface SeatRepository {

    List<Seat> findAvailableSeats(Long seanceId);
    void updateSeatStatus(Long seatId);

    List<Seat> getAllSeats();
}
