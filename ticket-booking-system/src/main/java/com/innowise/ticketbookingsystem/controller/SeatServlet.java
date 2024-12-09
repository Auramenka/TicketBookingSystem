package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.service.SeatService;
import com.innowise.ticketbookingsystem.service.impl.SeatServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/seats")
public class SeatServlet extends HttpServlet {

    private final SeatService seatService = new SeatServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long seanceId = Long.parseLong(req.getParameter("seanceId"));
        List<SeatDto> availableSeats = seatService.getAvailableSeats(seanceId);

        req.setAttribute("seats", availableSeats);
        req.getRequestDispatcher("/seats.jsp").forward(req, resp);
    }
}
