package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.model.Category;
import com.innowise.ticketbookingsystem.service.EventService;
import com.innowise.ticketbookingsystem.service.impl.EventServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/editEvent")
public class EditEventServlet extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        EventDto eventDto = eventService.getEventById(id);

        req.setAttribute("eventDto", eventDto);
        req.getRequestDispatcher("/editEvent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String dateStart = req.getParameter("dateStart");
        String dateEnd = req.getParameter("dateEnd");
        String category = req.getParameter("category");
        String photo = req.getParameter("photo");

        EventDto eventDto = eventService.getEventById(id);

        eventDto.setId(id);
        eventDto.setName(name);
        eventDto.setDescription(description);
        eventDto.setDateStart(LocalDate.parse(dateStart));
        eventDto.setDateEnd(LocalDate.parse(dateEnd));
        eventDto.setCategory(Category.valueOf(category));
        eventDto.setPhoto(photo);

        eventService.updateEvent(eventDto);

        resp.sendRedirect("/manageEvents");
    }
}
