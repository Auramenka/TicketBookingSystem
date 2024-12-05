package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.model.Seat;

import java.util.List;

public interface SeatRepository {

    List<Seat> getAllSeats();
}
