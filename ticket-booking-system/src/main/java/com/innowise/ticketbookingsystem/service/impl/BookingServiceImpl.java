package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.BookingDto;
import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.mappers.BookingMapper;
import com.innowise.ticketbookingsystem.mappers.SeanceMapper;
import com.innowise.ticketbookingsystem.mappers.SeatMapper;
import com.innowise.ticketbookingsystem.model.Booking;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.BookingRepository;
import com.innowise.ticketbookingsystem.repository.impl.BookingRepositoryImpl;
import com.innowise.ticketbookingsystem.service.BookingService;

import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository = new BookingRepositoryImpl();

    public BookingDto bookSeats(SeanceDto seanceDto, List<Long> seatIds) {
        Booking booking = new Booking();
        for (Long seatId : seatIds) {
            Seat seat = new Seat();
            seat.setId(seatId);
            booking.setSeance(SeanceMapper.toEntity(seanceDto));
            booking.setSeat(seat);
            booking.setIsOccupied(true);
            bookingRepository.saveBooking(booking);
        }
        return BookingMapper.toDto(booking);
    }

    public List<SeatDto> getAvailableSeats(SeanceDto seanceDto) {
        return bookingRepository.getAvailableSeats(SeanceMapper.toEntity(seanceDto))
                .stream().map(SeatMapper::toDto)
                .collect(Collectors.toList());
    }
}
