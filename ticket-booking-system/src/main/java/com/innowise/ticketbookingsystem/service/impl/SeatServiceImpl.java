package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.mappers.SeatMapper;
import com.innowise.ticketbookingsystem.repository.SeatRepository;
import com.innowise.ticketbookingsystem.repository.impl.SeatRepositoryImpl;
import com.innowise.ticketbookingsystem.service.SeatService;

public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository = new SeatRepositoryImpl();

    @Override
    public SeatDto getSeatById(Long seatId) {
        return SeatMapper.toDto(seatRepository.findById(seatId));
    }
}
