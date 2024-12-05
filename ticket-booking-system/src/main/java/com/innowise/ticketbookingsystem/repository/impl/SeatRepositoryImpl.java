package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.SeatRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SeatRepositoryImpl implements SeatRepository {

    public List<Seat> getAllSeats() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Seat ", Seat.class).list();
        }
    }
}
