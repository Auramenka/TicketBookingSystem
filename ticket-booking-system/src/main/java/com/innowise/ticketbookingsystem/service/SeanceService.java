package com.innowise.ticketbookingsystem.service;

import com.innowise.ticketbookingsystem.dto.SeanceDto;

import java.util.List;

public interface SeanceService {
    void addSeance(SeanceDto seanceDto);
    void deleteSeanceById(Long id);
    SeanceDto getSeanceById(Long seanceId);
    List<SeanceDto> getSeances();
    List<SeanceDto> getSeancesById(Long eventId);
}
