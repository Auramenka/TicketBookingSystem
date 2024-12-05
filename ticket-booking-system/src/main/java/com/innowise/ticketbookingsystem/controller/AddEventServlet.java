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

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventDto eventDto = new EventDto();
        eventDto.setName(req.getParameter("name"));
        eventDto.setDescription(req.getParameter("description"));
        eventDto.setDateStart(LocalDate.parse(req.getParameter("dateStart")));
        eventDto.setDateEnd(LocalDate.parse(req.getParameter("dateEnd")));
        eventDto.setCategory(Category.valueOf(req.getParameter("category")));
        eventDto.setPhoto(req.getParameter("photo"));

        eventService.addEvent(eventDto);

        resp.sendRedirect("manageEvents");
    }
}
