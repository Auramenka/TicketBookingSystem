package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Booking;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.BookingRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void saveBooking(Booking booking) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(booking);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public List<Seat> getAvailableSeats(Seance seance) {
        List<Seat> availableSeats = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Seat> allSeats = session.createQuery("FROM Seat", Seat.class).list();
            List<Booking> bookings = session.createQuery("FROM Booking WHERE seance = :seance", Booking.class)
                    .setParameter("seance", seance).list();
            List<Long> bookedSeatIds = bookings.stream()
                    .map(booking -> booking.getSeat().getId())
                    .toList();
            for (Seat seat : allSeats) {
                if (!bookedSeatIds.contains(seat.getId())) {
                    availableSeats.add(seat);
                }
            }
            session.getTransaction().commit();
        }
        return availableSeats;
    }
}
