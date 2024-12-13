package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.service.SeanceService;
import com.innowise.ticketbookingsystem.service.impl.SeanceServiceImpl;
import com.innowise.ticketbookingsystem.util.BookingUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/seance")
public class SeanceServlet extends HttpServlet {

    private final SeanceService seanceService = new SeanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SeanceDto> seances = BookingUtil.getSeances(req, seanceService);

        BookingUtil.putSeanceAttributes(req, seances);

        req.setAttribute("seances", seances);
        req.getRequestDispatcher("/seances.jsp").forward(req, resp);
    }
}
