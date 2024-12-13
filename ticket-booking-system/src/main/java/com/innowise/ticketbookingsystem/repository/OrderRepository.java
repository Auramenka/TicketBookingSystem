package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.model.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<OrderDto> findByUserId(Long userId);
}
