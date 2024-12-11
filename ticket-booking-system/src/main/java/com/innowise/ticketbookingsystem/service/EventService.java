package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.model.Category;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    void addEvent(EventDto eventDto);
    void deleteEventById(Long id);
    void updateEvent(EventDto eventDto);
    List<EventDto> getEventsByCategory(Category category);
    EventDto getEventById(Long id);
    List<EventDto> getEvents();
    List<EventDto> getEventsByDateRange(LocalDate startDate, LocalDate endDate);
    List<EventDto> getUpcomingEvents();
}
