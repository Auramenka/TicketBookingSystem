package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.model.Booking;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.BookingRepository;
import com.innowise.ticketbookingsystem.repository.impl.BookingRepositoryImpl;
import com.innowise.ticketbookingsystem.service.BookingService;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository = new BookingRepositoryImpl();

    public void bookSeats(Seance seance, List<Long> seatIds) {
        for (Long seatId : seatIds) {
            Seat seat = new Seat();
            seat.setId(seatId);
            Booking booking = new Booking();
            booking.setSeance(seance);
            booking.setSeat(seat);
            booking.setIsOccupied(true);
            bookingRepository.saveBooking(booking);
        }
    }

    public List<Seat> getAvailableSeats(Seance seance) {
        return bookingRepository.getAvailableSeats(seance);
    }
}
