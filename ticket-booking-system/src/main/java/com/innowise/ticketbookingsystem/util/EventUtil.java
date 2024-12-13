package com.innowise.ticketbookingsystem.util;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.exceptions.EventNotFoundException;
import com.innowise.ticketbookingsystem.service.EventService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

public class EventUtil {

    public static EventDto handleEventRequest(HttpServletRequest req, EventService eventService) {
        String eventIdParam = req.getParameter("id");
        EventDto event;

        if (Objects.nonNull(eventIdParam)) {
            Long eventId = Long.valueOf(eventIdParam);
            event = eventService.getEventById(eventId);

            BookingUtil.put("eventId", eventId);
            BookingUtil.put("eventName", event.getName());
        } else {
            throw new EventNotFoundException("Event ID is missing");
        }

        return event;
    }
}
