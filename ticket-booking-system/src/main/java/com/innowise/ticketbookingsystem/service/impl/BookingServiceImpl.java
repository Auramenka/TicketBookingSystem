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
import com.innowise.ticketbookingsystem.service.SeanceService;
import com.innowise.ticketbookingsystem.service.SeatService;
import com.innowise.ticketbookingsystem.util.BookingUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository = new BookingRepositoryImpl();
    private final SeanceService seanceService = new SeanceServiceImpl();
    private final SeatService seatService = new SeatServiceImpl();

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
                .stream()
                .map(SeatMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookingDto bookSeatsAndGetSeatDetails(Long seanceId, String[] selectedSeats) {
        BookingDto bookingDto = null;
        List<String> seatDetails = new ArrayList<>();
        if (selectedSeats != null) {
            List<Long> seatIds = Arrays.stream(selectedSeats).map(Long::parseLong).collect(Collectors.toList());
            SeanceDto seanceDto = seanceService.getSeanceById(seanceId);
            seanceDto.setId(seanceId);
            bookingDto = bookSeats(seanceDto, seatIds);

            for (Long seatId : seatIds) {
                Seat seat = SeatMapper.toEntity(seatService.getSeatById(seatId));
                seatDetails.add("Ряд: " + seat.getRowNumber() + ", Место: " + seat.getSeatNumber());
            }
        }

        BookingUtil.put("seats", seatDetails);
        return bookingDto;
    }
}
