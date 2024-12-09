package com.innowise.ticketbookingsystem.mappers;

import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.model.Seat;

public class SeatMapper {

    public static Seat toEntity(SeatDto seatDto) {
        Seat seat = new Seat();
        seat.setId(seatDto.getId());
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setRowNumber(seatDto.getRowNumber());
        return seat;
    }

    public static SeatDto toDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setId(seat.getId());
        seatDto.setSeatNumber(seat.getSeatNumber());
        seatDto.setRowNumber(seat.getRowNumber());
        return seatDto;
    }
}
