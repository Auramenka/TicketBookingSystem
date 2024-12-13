package com.innowise.ticketbookingsystem.util;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.exceptions.EventNotFoundException;
import com.innowise.ticketbookingsystem.service.SeanceService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

public class BookingUtil {

    private static final Map<String, Object> bookingData = new HashMap<>();

    public static void put(String key, Object value) {
        bookingData.put(key, value);
    }

    public static Object get(String key) {
        return bookingData.get(key);
    }

    public static void clear() {
        bookingData.clear();
    }

    public static List<SeanceDto> getSeances(HttpServletRequest req, SeanceService seanceService) {
        String eventIdParam = req.getParameter("eventId");
        List<SeanceDto> seances = new ArrayList<>();

        if (Objects.nonNull(eventIdParam)) {
            try {
                Long eventId = Long.parseLong(eventIdParam);
                seances = seanceService.getSeancesById(eventId);
            } catch (EventNotFoundException e) {
                req.setAttribute("errorMessage", "Неверный ID мероприятия");
            }
        }
        return seances;
    }

    public static void putSeanceAttributes(List<SeanceDto> seances) {
        for (SeanceDto seanceDto : seances) {
            BookingUtil.put("seanceId", seanceDto.getId());
            BookingUtil.put("seanceDate", seanceDto.getDateStart());
            BookingUtil.put("seanceTime", seanceDto.getTimeStart());
        }
    }
}
