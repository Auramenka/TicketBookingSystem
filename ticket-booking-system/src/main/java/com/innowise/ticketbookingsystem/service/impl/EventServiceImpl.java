package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.mappers.EventMapper;
import com.innowise.ticketbookingsystem.model.Category;
import com.innowise.ticketbookingsystem.model.Event;
import com.innowise.ticketbookingsystem.repository.EventRepository;
import com.innowise.ticketbookingsystem.repository.impl.EventRepositoryImpl;
import com.innowise.ticketbookingsystem.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository = new EventRepositoryImpl();

    public List<EventDto> getEventsByCategory(Category category) {
        List<Event> events = eventRepository.findByCategory(category);
        return events.stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto getEventById(Long id) {
        return EventMapper.toDto(eventRepository.findById(id));
    }

    public List<EventDto> getEvents() {
        List<Event> events = eventRepository.getAllEvents();
        return events.stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }
}
