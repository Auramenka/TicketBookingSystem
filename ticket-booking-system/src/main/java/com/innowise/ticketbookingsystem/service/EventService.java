package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.model.Category;

import java.util.List;

public interface EventService {
    List<EventDto> getEventsByCategory(Category category);
    EventDto getEventById(Long id);
    List<EventDto> getEvents();
}
