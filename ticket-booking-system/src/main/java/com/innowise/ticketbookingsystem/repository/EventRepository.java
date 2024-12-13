package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.model.enums.Category;
import com.innowise.ticketbookingsystem.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository {

    void save(Event event);
    void deleteById(Long id);
    void update(Event event);
    Event findById(Long id);
    List<Event> findByCategory(Category category);
    List<Event> getAllEvents();
    List<Event> findByDateRange(LocalDate startDate, LocalDate endDate);
    List<Event> findUpcomingEvents(LocalDate today);
}
