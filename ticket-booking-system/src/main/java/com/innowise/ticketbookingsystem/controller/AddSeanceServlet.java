package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.service.SeanceService;
import com.innowise.ticketbookingsystem.service.impl.SeanceServiceImpl;
import com.innowise.ticketbookingsystem.util.MapperUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addSeance")
public class AddSeanceServlet extends HttpServlet {

    private final SeanceService seanceService = new SeanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long eventId = Long.parseLong(req.getParameter("eventId"));
        req.setAttribute("eventId", eventId);

        req.getRequestDispatcher("/addSeance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SeanceDto seanceDto = MapperUtility.mapRequestToSeanceDto(req);

        seanceService.addSeance(seanceDto);

        req.setAttribute("eventId", seanceDto.getEventId());

        req.getRequestDispatcher("/addSeanceSuccess.jsp").forward(req, resp);
    }
}
