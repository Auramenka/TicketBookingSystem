package com.innowise.ticketbookingsystem.mappers;

import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.model.Seat;

public class SeatMapper {

    public static Seat toEntity(SeatDto seatDto) {
        Seat seat = new Seat();
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setRowNumber(seatDto.getRowNumber());
        seat.setIsOccupied(seatDto.getIsOccupied());
        return seat;
    }

    public static SeatDto toDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setSeatNumber(seat.getSeatNumber());
        seatDto.setRowNumber(seat.getRowNumber());
        seatDto.setIsOccupied(seat.getIsOccupied());
        return seatDto;
    }
}
