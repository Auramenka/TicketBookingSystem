package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.model.*;
import com.innowise.ticketbookingsystem.repository.BookingRepository;
import com.innowise.ticketbookingsystem.repository.EventRepository;
import com.innowise.ticketbookingsystem.repository.OrderRepository;
import com.innowise.ticketbookingsystem.repository.UserRepository;
import com.innowise.ticketbookingsystem.repository.impl.BookingRepositoryImpl;
import com.innowise.ticketbookingsystem.repository.impl.EventRepositoryImpl;
import com.innowise.ticketbookingsystem.repository.impl.OrderRepositoryImpl;
import com.innowise.ticketbookingsystem.repository.impl.UserRepositoryImpl;
import com.innowise.ticketbookingsystem.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository = new OrderRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final BookingRepository bookingRepository = new BookingRepositoryImpl();
    private final EventRepository eventRepository = new EventRepositoryImpl();

    @Override
    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderStatus(orderDto.getOrderStatus());

        User user = userRepository.findById(orderDto.getUserId());
        Event event = eventRepository.findById(orderDto.getEventId());
        Booking booking = bookingRepository.findById(orderDto.getBookingId());

        order.setUser(user);
        order.setEvent(event);
        order.setBooking(booking);

        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}