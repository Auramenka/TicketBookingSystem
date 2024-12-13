package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.exceptions.SeatNotFoundException;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.SeatRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

@Slf4j
public class SeatRepositoryImpl implements SeatRepository {

    private static final String FIND_AVAILABLE_SEATS =
            "FROM Seat s WHERE s.id NOT IN (SELECT ss.seat.id FROM Booking ss WHERE ss.seance.id = :seanceId)";
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Seat findById(Long id) {
        Seat seat;
        try (Session session = sessionFactory.openSession()) {
            seat = session.get(Seat.class, id);
            if (Objects.isNull(seat)) {
                log.error("Seat with id " + id + " not found.");
                throw new SeatNotFoundException("Seat with id " + id + " not found.");
            }
        }
        return seat;
    }

    public List<Seat> findAvailableSeats(Long seanceId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Seat> query = session.createQuery(FIND_AVAILABLE_SEATS, Seat.class);
            query.setParameter("seanceId", seanceId);
            return query.list();
        }
    }
}
