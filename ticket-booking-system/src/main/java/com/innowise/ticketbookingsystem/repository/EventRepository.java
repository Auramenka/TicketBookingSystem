package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.model.Category;
import com.innowise.ticketbookingsystem.model.Event;

import java.util.List;

public interface EventRepository {

    void save(Event event);
    void deleteById(Long id);
    void update(Event event);
    Event findById(Long id);
    List<Event> findByCategory(Category category);
    List<Event> getAllEvents();
}
