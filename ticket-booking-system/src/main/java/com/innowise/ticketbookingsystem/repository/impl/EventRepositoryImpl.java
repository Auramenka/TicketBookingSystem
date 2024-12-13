package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.exceptions.EventNotFoundException;
import com.innowise.ticketbookingsystem.exceptions.RollbackException;
import com.innowise.ticketbookingsystem.model.enums.Category;
import com.innowise.ticketbookingsystem.model.Event;
import com.innowise.ticketbookingsystem.repository.EventRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
public class EventRepositoryImpl implements EventRepository {

    private static final String SELECT_ALL_EVENTS = "FROM Event";
    private static final String SELECT_EVENTS_BY_CATEGORY = "FROM Event WHERE category = :category";
    private static final String SELECT_EVENTS_BY_DATE_RANGE = "FROM Event WHERE dateStart >= :startDate AND dateEnd <= :endDate";
    private static final String SELECT_UPCOMING_EVENTS = "FROM Event WHERE dateStart >= :today";

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(Event event) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(event);
                transaction.commit();
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Rollback occurred while saving event: {}", event, e);
            }
        }
    }

    public void deleteById(Long id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Event event = session.get(Event.class, id);
                if (Objects.nonNull(event)) {
                    session.delete(event);
                } else {
                    log.error("Event with id " + id + " not found.");
                    throw new EventNotFoundException("Event with id " + id + " not found.");
                }
                transaction.commit();
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Rollback occurred while deleting event with id {}: {}", id, e);
            }
        }
    }

    public void update(Event event) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(event);
                transaction.commit();
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Rollback occurred while updating event: {}", event, e);
            }
        }
    }

    @Override
    public Event findById(Long id) {
        Event event;
        try (Session session = sessionFactory.openSession()) {
            event = session.get(Event.class, id);
            if (Objects.isNull(event)) {
                log.error("Event with id " + id + " not found.");
                throw new EventNotFoundException("Event with id " + id + " not found.");
            }
        }
        return event;
    }

    public List<Event> findByCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_EVENTS_BY_CATEGORY, Event.class)
                        .setParameter("category", category)
                        .getResultList();
        }
    }

    public List<Event> getAllEvents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(SELECT_ALL_EVENTS, Event.class).list();
        }
    }

    @Override
    public List<Event> findByDateRange(LocalDate startDate, LocalDate endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(SELECT_EVENTS_BY_DATE_RANGE, Event.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }
    }

    @Override
    public List<Event> findUpcomingEvents(LocalDate today) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_UPCOMING_EVENTS, Event.class)
                    .setParameter("today", today)
                    .getResultList();
        }
    }
}
