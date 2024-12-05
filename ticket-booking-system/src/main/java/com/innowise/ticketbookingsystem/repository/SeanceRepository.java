package com.innowise.ticketbookingsystem.repository;

import com.innowise.ticketbookingsystem.model.Seance;

import java.util.List;

public interface SeanceRepository {
    void save(Seance seance);
    List<Seance> getAllSeances();
    List<Seance> getSeancesByEventId(Long eventId);
}
