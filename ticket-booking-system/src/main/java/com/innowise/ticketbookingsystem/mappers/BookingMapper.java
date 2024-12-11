package com.innowise.ticketbookingsystem.mappers;

import com.innowise.ticketbookingsystem.dto.BookingDto;
import com.innowise.ticketbookingsystem.model.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingMapper {

    public static BookingDto toDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setIsOccupied(booking.getIsOccupied());
        bookingDto.setSeanceId(booking.getSeance().getId());
        bookingDto.setSeatId(booking.getSeat().getId());
        return bookingDto;
    }
}
