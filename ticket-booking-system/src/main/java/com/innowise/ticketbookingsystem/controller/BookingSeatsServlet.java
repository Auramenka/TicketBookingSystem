package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.service.BookingService;
import com.innowise.ticketbookingsystem.service.SeanceService;
import com.innowise.ticketbookingsystem.service.impl.BookingServiceImpl;
import com.innowise.ticketbookingsystem.service.impl.SeanceServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/bookSeats")
public class BookingSeatsServlet extends HttpServlet {

    private final SeanceService seanceService = new SeanceServiceImpl();
    private final BookingService bookingService = new BookingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long seanceId = Long.parseLong(req.getParameter("seanceId"));
        Seance seance = seanceService.getSeanceById(seanceId);
        seance.setId(seanceId);

        List<Seat> availableSeats = bookingService.getAvailableSeats(seance);

        req.setAttribute("availableSeats", availableSeats);
        req.setAttribute("seanceId", seanceId);

        req.getRequestDispatcher("/booking.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long seanceId = Long.parseLong(req.getParameter("seanceId"));
        String[] selectedSeats = req.getParameterValues("seats");

        if (selectedSeats != null) {
            List<Long> seatIds = Arrays.stream(selectedSeats).map(Long::parseLong).collect(Collectors.toList());
            Seance seance = seanceService.getSeanceById(seanceId);
            seance.setId(seanceId);
            bookingService.bookSeats(seance, seatIds);
        }

        req.setAttribute("seats", selectedSeats);
        req.getRequestDispatcher("orderPreview.jsp").forward(req, resp);
    }
}
