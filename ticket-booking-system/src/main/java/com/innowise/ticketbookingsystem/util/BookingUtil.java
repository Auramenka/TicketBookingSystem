package com.innowise.ticketbookingsystem.util;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.service.SeanceService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (eventIdParam != null) {
            try {
                Long eventId = Long.parseLong(eventIdParam);
                seances = seanceService.getSeancesById(eventId);
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Неверный ID мероприятия");
            }
        }
        return seances;
    }

    public static void putSeanceAttributes(HttpServletRequest req, List<SeanceDto> seances) {
        for (SeanceDto seanceDto : seances) {
            req.setAttribute("seanceId", seanceDto.getId());
            req.setAttribute("seanceDate", seanceDto.getDateStart());
            req.setAttribute("seanceTime", seanceDto.getTimeStart());
        }
    }
}
