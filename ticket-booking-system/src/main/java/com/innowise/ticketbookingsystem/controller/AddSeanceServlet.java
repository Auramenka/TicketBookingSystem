package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.service.SeanceService;
import com.innowise.ticketbookingsystem.service.impl.SeanceServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/addSeance")
public class AddSeanceServlet extends HttpServlet {

    private final SeanceService seanceService = new SeanceServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate dateStart = LocalDate.parse(req.getParameter("dateStart"));
        LocalTime timeStart = LocalTime.parse(req.getParameter("timeStart"));
        Long eventId = Long.parseLong(req.getParameter("eventId"));

        seanceService.addSeance(dateStart, timeStart, eventId);

        req.setAttribute("eventId", eventId);

        req.getRequestDispatcher("/addSeanceSuccess.jsp").forward(req, resp);
    }
}
