package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.exceptions.EventNotFoundException;
import com.innowise.ticketbookingsystem.mappers.EventMapper;
import com.innowise.ticketbookingsystem.model.enums.Category;
import com.innowise.ticketbookingsystem.model.Event;
import com.innowise.ticketbookingsystem.repository.EventRepository;
import com.innowise.ticketbookingsystem.repository.impl.EventRepositoryImpl;
import com.innowise.ticketbookingsystem.service.EventService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository = new EventRepositoryImpl();

    public void addEvent(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setDateStart(eventDto.getDateStart());
        event.setDateEnd(eventDto.getDateEnd());
        event.setCategory(eventDto.getCategory());
        event.setPhoto(eventDto.getPhoto());
        eventRepository.save(event);
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    public void updateEvent(EventDto eventDto) {
        Event event = eventRepository.findById(eventDto.getId());
        if (Objects.nonNull(event)) {
            event.setId(eventDto.getId());
            event.setName(eventDto.getName());
            event.setDescription(eventDto.getDescription());
            event.setDateStart(eventDto.getDateStart());
            event.setDateEnd(eventDto.getDateEnd());
            event.setCategory(eventDto.getCategory());
            event.setPhoto(eventDto.getPhoto());
            eventRepository.update(event);
        } else {
            log.error("Мероприятие не найдено");
            throw new EventNotFoundException("Мероприятие не найдено");
        }
    }

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

    @Override
    public List<EventDto> getEventsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Event> events = eventRepository.findByDateRange(startDate, endDate);
        return events.stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getUpcomingEvents() {
        LocalDate today = LocalDate.now();
        List<Event> events = eventRepository.findUpcomingEvents(today);
        return events.stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }
}