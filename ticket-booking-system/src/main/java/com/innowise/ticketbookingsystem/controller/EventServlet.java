package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.service.EventService;
import com.innowise.ticketbookingsystem.service.impl.EventServiceImpl;
import com.innowise.ticketbookingsystem.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/events")
public class EventServlet extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryParam = req.getParameter("category");
        String startDateParam = req.getParameter("startDate");
        String endDateParam = req.getParameter("endDate");

        List<EventDto> events;

        if (startDateParam != null && endDateParam != null) {
            LocalDate startDate = LocalDate.parse(startDateParam);
            LocalDate endDate = LocalDate.parse(endDateParam);
            events = eventService.getEventsByDateRange(startDate, endDate);
        } else if (categoryParam != null) {
            Category category = Category.valueOf(categoryParam.toUpperCase());
            events = eventService.getEventsByCategory(category);
        } else {
            events = eventService.getUpcomingEvents();
        }

        req.setAttribute("events", events);
        req.getRequestDispatcher("/events.jsp").forward(req, resp);
    }
}
