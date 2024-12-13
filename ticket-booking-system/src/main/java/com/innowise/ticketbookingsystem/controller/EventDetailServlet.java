package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.service.EventService;
import com.innowise.ticketbookingsystem.service.impl.EventServiceImpl;
import com.innowise.ticketbookingsystem.util.EventUtil;
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
        EventDto event;

        event = EventUtil.handleEventRequest(req, eventService);

        req.setAttribute("event", event);
        req.getRequestDispatcher("/event-details.jsp").forward(req, resp);
    }
}
