package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.service.EventService;
import com.innowise.ticketbookingsystem.service.impl.EventServiceImpl;
import com.innowise.ticketbookingsystem.util.MapperUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventDto eventDto = MapperUtility.mapRequestToEventDto(req);

        eventService.addEvent(eventDto);

        resp.sendRedirect("manageEvents");
    }
}
