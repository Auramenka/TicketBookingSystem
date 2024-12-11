package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.SeatRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SeatRepositoryImpl implements SeatRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Seat findById(Long id) {
        Seat seat;
        try (Session session = sessionFactory.openSession()) {
            seat = session.get(Seat.class, id);
            if (seat == null) {
                System.out.println("Seat with id " + id + " not found.");
            }
        }
        return seat;
    }

    public List<Seat> findAvailableSeats(Long seanceId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Seat s WHERE s.id NOT IN (SELECT ss.seat.id FROM Booking ss WHERE ss.seance.id = :seanceId)";
            Query<Seat> query = session.createQuery(hql, Seat.class);
            query.setParameter("seanceId", seanceId);
            return query.list();
        }
    }
}
