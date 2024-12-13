package com.innowise.ticketbookingsystem.service.impl;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.exceptions.EventNotFoundException;
import com.innowise.ticketbookingsystem.mappers.SeanceMapper;
import com.innowise.ticketbookingsystem.model.Event;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.repository.EventRepository;
import com.innowise.ticketbookingsystem.repository.SeanceRepository;
import com.innowise.ticketbookingsystem.repository.impl.EventRepositoryImpl;
import com.innowise.ticketbookingsystem.repository.impl.SeanceRepositoryImpl;
import com.innowise.ticketbookingsystem.service.SeanceService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository = new SeanceRepositoryImpl();
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public void addSeance(LocalDate dateStart, LocalTime timeStart, Long eventId) {
        Seance seance = new Seance();
        seance.setDateStart(dateStart);
        seance.setTimeStart(timeStart);

        Event event = eventRepository.findById(eventId);
        if (!Objects.isNull(event)) {
            seance.setEvent(event);
            seanceRepository.save(seance);
        } else {
            log.error("Event not found with ID: " + eventId);
            throw new EventNotFoundException("Event not found with ID: " + eventId);
        }
    }

    @Override
    public void deleteSeanceById(Long id) {
        seanceRepository.deleteById(id);
    }

    public SeanceDto getSeanceById(Long seanceId) {
        return SeanceMapper.toDto(seanceRepository.findById(seanceId));
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
