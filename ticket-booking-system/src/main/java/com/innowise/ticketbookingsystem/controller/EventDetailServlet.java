package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.service.EventService;
import com.innowise.ticketbookingsystem.service.impl.EventServiceImpl;
import com.innowise.ticketbookingsystem.util.BookingUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/eventDetail")
public class EventDetailServlet extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventIdParam = req.getParameter("id");
        EventDto event;

        if (eventIdParam != null) {
            Long eventId = Long.valueOf(eventIdParam);
            event = eventService.getEventById(eventId);

            BookingUtil.put("eventId", eventId);
            BookingUtil.put("eventName", event.getName());
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is missing");
            return;
        }

        req.setAttribute("event", event);
        req.getRequestDispatcher("/event-details.jsp").forward(req, resp);
    }
}
