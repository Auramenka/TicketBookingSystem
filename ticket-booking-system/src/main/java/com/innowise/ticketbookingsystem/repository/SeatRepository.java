package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.model.Seat;

import java.util.List;

public interface SeatRepository {

    Seat findById(Long id);
    List<Seat> findAvailableSeats(Long seanceId);

}
