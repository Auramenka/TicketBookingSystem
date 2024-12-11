package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.BookingDto;
import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.dto.SeatDto;
import com.innowise.ticketbookingsystem.mappers.SeatMapper;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.service.BookingService;
import com.innowise.ticketbookingsystem.service.SeanceService;
import com.innowise.ticketbookingsystem.service.SeatService;
import com.innowise.ticketbookingsystem.service.impl.BookingServiceImpl;
import com.innowise.ticketbookingsystem.service.impl.SeanceServiceImpl;
import com.innowise.ticketbookingsystem.service.impl.SeatServiceImpl;
import com.innowise.ticketbookingsystem.util.BookingUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/bookSeats")
public class BookingSeatsServlet extends HttpServlet {

    private final SeanceService seanceService = new SeanceServiceImpl();
    private final BookingService bookingService = new BookingServiceImpl();
    private final SeatService seatService = new SeatServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long seanceId = Long.parseLong(req.getParameter("seanceId"));
        SeanceDto seanceDto = seanceService.getSeanceById(seanceId);
        seanceDto.setId(seanceId);

        List<SeatDto> availableSeats = bookingService.getAvailableSeats(seanceDto);

        req.setAttribute("availableSeats", availableSeats);
        req.setAttribute("seanceId", seanceId);

        req.getRequestDispatcher("/booking.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long seanceId = Long.parseLong(req.getParameter("seanceId"));
        String[] selectedSeats = req.getParameterValues("seats");

        BookingDto bookingDto = null;
        List<String> seatDetails = new ArrayList<>();
        if (selectedSeats != null) {
            List<Long> seatIds = Arrays.stream(selectedSeats).map(Long::parseLong).collect(Collectors.toList());
            SeanceDto seanceDto = seanceService.getSeanceById(seanceId);
            seanceDto.setId(seanceId);
            bookingDto = bookingService.bookSeats(seanceDto, seatIds);

            for (Long seatId : seatIds) {
                Seat seat = SeatMapper.toEntity(seatService.getSeatById(seatId));
                seatDetails.add("Ряд: " + seat.getRowNumber() + ", Место: " + seat.getSeatNumber());
            }
        }

        BookingUtil.put("bookingId", bookingDto.getId());
        BookingUtil.put("seats", seatDetails);

        req.setAttribute("seats", seatDetails);
        req.getRequestDispatcher("orderPreview.jsp").forward(req, resp);
    }
}
