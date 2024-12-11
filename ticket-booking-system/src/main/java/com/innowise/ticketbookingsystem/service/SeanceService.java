package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.SeanceDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SeanceService {
    void addSeance(LocalDate dateStart, LocalTime timeStart, Long eventId);
    void deleteServiceById(Long id);
    SeanceDto getSeanceById(Long seanceId);
    List<SeanceDto> getSeances();
    List<SeanceDto> getSeancesById(Long eventId);
}
