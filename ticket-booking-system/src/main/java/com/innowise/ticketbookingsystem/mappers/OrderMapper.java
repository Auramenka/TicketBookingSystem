package com.innowise.ticketbookingsystem.mappers;

import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.model.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMapper {

    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setEventId(order.getEvent().getId());
        orderDto.setBookingId(order.getBooking().getId());
        return orderDto;
    }
}
