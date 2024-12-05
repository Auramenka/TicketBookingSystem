package com.innowise.ticketbookingsystem.mappers;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.model.Seance;

public class SeanceMapper {

    public static Seance toEntity(SeanceDto seanceDto) {
        Seance seance = new Seance();
        seance.setId(seanceDto.getId());
        seance.setDateStart(seanceDto.getDateStart());
        seance.setTimeStart(seanceDto.getTimeStart());
        return seance;
    }

    public static SeanceDto toDto(Seance seance) {
        SeanceDto seanceDto = new SeanceDto();
        seanceDto.setId(seance.getId());
        seanceDto.setDateStart(seance.getDateStart());
        seanceDto.setTimeStart(seance.getTimeStart());
        seanceDto.setEventId(seance.getEvent().getId());
        return seanceDto;
    }
}
