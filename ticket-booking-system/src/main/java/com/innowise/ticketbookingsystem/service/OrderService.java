package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void createOrder(OrderDto orderDto);
    List<OrderDto> getOrdersByUserId(Long userId);
}
