package com.innowise.ticketbookingsystem.controller;

import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.service.SeanceService;
import com.innowise.ticketbookingsystem.service.impl.SeanceServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteSeance")
public class DeleteSeanceServlet extends HttpServlet {

    private final SeanceService seanceService = new SeanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        SeanceDto seanceDto = seanceService.getSeanceById(id);

        req.setAttribute("seance", seanceDto);
        req.getRequestDispatcher("/deleteSeance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        seanceService.deleteServiceById(id);

        resp.sendRedirect("/events");
    }
}
