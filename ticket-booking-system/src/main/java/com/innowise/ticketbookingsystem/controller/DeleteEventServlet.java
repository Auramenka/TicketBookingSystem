package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.service.EventService;
import com.innowise.ticketbookingsystem.service.impl.EventServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteEvent")
public class DeleteEventServlet extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        EventDto eventDto = eventService.getEventById(id);

        req.setAttribute("eventDto", eventDto);
        req.getRequestDispatcher("/deleteEvent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        eventService.deleteEventById(id);

        resp.sendRedirect("/events");
    }
}
