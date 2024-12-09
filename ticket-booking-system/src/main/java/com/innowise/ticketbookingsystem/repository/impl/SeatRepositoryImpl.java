package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.SeatRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SeatRepositoryImpl implements SeatRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public List<Seat> findAvailableSeats(Long seanceId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Seat s WHERE s.id NOT IN (SELECT ss.seat.id FROM Booking ss WHERE ss.seance.id = :seanceId)";
            Query<Seat> query = session.createQuery(hql, Seat.class);
            query.setParameter("seanceId", seanceId);
            return query.list();
        }
    }

    public void updateSeatStatus(Long seatId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Seat seat = session.get(Seat.class, seatId);
            if (seat != null) {
                //seat.setIsOccupied(true);
                session.update(seat);
                transaction.commit();
            }
        } catch (PersistenceException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public List<Seat> getAllSeats() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Seat ", Seat.class).list();
        }
    }
}
