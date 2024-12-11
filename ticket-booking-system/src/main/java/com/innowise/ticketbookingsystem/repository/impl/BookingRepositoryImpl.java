package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Booking;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.BookingRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import jakarta.persistence.LockModeType;
import org.hibernate.LockMode;
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
                // Блокируем место, которое собираемся забронировать
                Seat seat = session.get(Seat.class, booking.getSeat().getId(), LockMode.PESSIMISTIC_WRITE);
                if (seat != null) {
                    session.save(booking);
                    transaction.commit();
                } else {
                    System.out.println("Seat not found for booking");
                    if (transaction != null) transaction.rollback();
                }
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public Booking findById(Long id) {
        Booking booking;
        try (Session session = sessionFactory.openSession()) {
            booking = session.get(Booking.class, id);
            if (booking == null) {
                System.out.println("Booking with id " + id + " not found.");
            }
        }
        return booking;
    }

    public List<Seat> getAvailableSeats(Seance seance) {
        List<Seat> availableSeats = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            try {

                List<Seat> allSeats = session.createQuery("FROM Seat", Seat.class)
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .list();

                //получаем все забронированные места для текущего сеанса
                List<Booking> bookings = session.createQuery("FROM Booking WHERE seance = :seance", Booking.class)
                        .setParameter("seance", seance).list();
                List<Long> bookedSeatIds = bookings.stream()
                        .map(booking -> booking.getSeat().getId())
                        .toList();

                //определяем доступные места
                for (Seat seat : allSeats) {
                    if (!bookedSeatIds.contains(seat.getId())) {
                        availableSeats.add(seat);
                    }
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
        return availableSeats;
    }
}