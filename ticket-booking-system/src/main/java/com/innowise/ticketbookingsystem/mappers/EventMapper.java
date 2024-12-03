package com.innowise.ticketbookingsystem.mappers;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.model.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventMapper {
    public static Event toEntity(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setDateStart(eventDto.getDateStart());
        event.setDateEnd(eventDto.getDateEnd());
        event.setPhoto(eventDto.getPhoto());
        return event;
    }

    public static EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setDateStart(event.getDateStart());
        eventDto.setDateEnd(event.getDateEnd());
        eventDto.setPhoto(event.getPhoto());
        return eventDto;
    }
}
