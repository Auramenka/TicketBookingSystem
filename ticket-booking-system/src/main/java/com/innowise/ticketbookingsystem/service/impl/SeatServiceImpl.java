package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.mappers.SeatMapper;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.SeatRepository;
import com.innowise.ticketbookingsystem.repository.impl.SeatRepositoryImpl;
import com.innowise.ticketbookingsystem.service.SeatService;

import java.util.List;
import java.util.stream.Collectors;

public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository = new SeatRepositoryImpl();

    public List<SeatDto> getSeats() {
        List<Seat> allSeats = seatRepository.getAllSeats();
        return allSeats.stream().map(SeatMapper::toDto).collect(Collectors.toList());
    }
}
