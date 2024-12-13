package com.innowise.ticketbookingsystem.dto;

import com.innowise.ticketbookingsystem.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private OrderStatus orderStatus;
    private Long userId;
    private Long eventId;
    private Long bookingId;

    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String seatInfo;
}