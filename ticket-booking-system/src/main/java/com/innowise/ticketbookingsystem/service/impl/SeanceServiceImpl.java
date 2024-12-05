package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.mappers.SeanceMapper;
import com.innowise.ticketbookingsystem.model.Event;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.repository.EventRepository;
import com.innowise.ticketbookingsystem.repository.SeanceRepository;
import com.innowise.ticketbookingsystem.repository.impl.EventRepositoryImpl;
import com.innowise.ticketbookingsystem.repository.impl.SeanceRepositoryImpl;
import com.innowise.ticketbookingsystem.service.SeanceService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository = new SeanceRepositoryImpl();
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public void addSeance(LocalDate dateStart, LocalTime timeStart, Long eventId) {
        Seance seance = new Seance();
        seance.setDateStart(dateStart);
        seance.setTimeStart(timeStart);

        Event event = eventRepository.findById(eventId);
        if (event != null) {
            seance.setEvent(event);
            seanceRepository.save(seance);
        } else {
            throw new IllegalArgumentException("Event not found with ID: " + eventId);
        }
    }

    public List<SeanceDto> getSeances() {
        List<Seance> seances = seanceRepository.getAllSeances();
        return seances.stream()
                .map(SeanceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<SeanceDto> getSeancesById(Long eventId) {
        List<Seance> seances = seanceRepository.getSeancesByEventId(eventId);
        return seances.stream()
                .map(SeanceMapper::toDto)
                .collect(Collectors.toList());
    }

}
