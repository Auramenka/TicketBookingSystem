package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.exceptions.BookingNotFoundException;
import com.innowise.ticketbookingsystem.exceptions.RollbackException;
import com.innowise.ticketbookingsystem.model.Booking;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.model.Seat;
import com.innowise.ticketbookingsystem.repository.BookingRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import jakarta.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class BookingRepositoryImpl implements BookingRepository {

    private static final String SELECT_ALL_SEATS = "FROM Seat";
    private static final String SELECT_BOOKINGS_BY_SEANCE = "FROM Booking WHERE seance = :seance";

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void saveBooking(Booking booking) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                // Блокируем место, которое собираемся забронировать
                Seat seat = session.get(Seat.class, booking.getSeat().getId(), LockMode.PESSIMISTIC_WRITE);
                if (Objects.nonNull(seat)) {
                    session.save(booking);
                    transaction.commit();
                } else {
                    log.error("Seat not found for booking");
                    if (Objects.nonNull(transaction)) transaction.rollback();
                    throw new RollbackException("Seat not found for booking");
                }
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("An error occurred while saving the booking");
            }
        }
    }

    @Override
    public Booking findById(Long id) {
        Booking booking;
        try (Session session = sessionFactory.openSession()) {
            booking = session.get(Booking.class, id);
            if (Objects.isNull(booking)) {
                log.error("Booking with id " + id + " not found.");
                throw new BookingNotFoundException("Booking with id " + id + " not found.");
            }
        }
        return booking;
    }

    public List<Seat> getAvailableSeats(Seance seance) {
        List<Seat> availableSeats = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                List<Seat> allSeats = session.createQuery(SELECT_ALL_SEATS, Seat.class)
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .list();

                //получаем все забронированные места для текущего сеанса
                List<Booking> bookings = session.createQuery(SELECT_BOOKINGS_BY_SEANCE, Booking.class)
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
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Failed to get available seats for seance: " + seance, e);
            }
        }
        return availableSeats;
    }
}