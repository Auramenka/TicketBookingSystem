package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.model.Seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SeanceService {
    void addSeance(LocalDate dateStart, LocalTime timeStart, Long eventId);
    void deleteServiceById(Long id);
    Seance getSeanceById(Long seanceId);
    List<SeanceDto> getSeances();
    List<SeanceDto> getSeancesById(Long eventId);
}
