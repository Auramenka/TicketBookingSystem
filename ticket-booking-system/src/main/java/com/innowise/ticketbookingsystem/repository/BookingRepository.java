package com.innowise.ticketbookingsystem.repository;
import com.innowise.ticketbookingsystem.model.Booking;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;

import java.util.List;

public interface BookingRepository {
    void saveBooking(Booking booking);
    List<Seat> getAvailableSeats(Seance seance);
}
