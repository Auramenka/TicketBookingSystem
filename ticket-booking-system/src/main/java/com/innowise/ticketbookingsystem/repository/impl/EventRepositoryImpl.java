package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Category;
import com.innowise.ticketbookingsystem.model.Event;
import com.innowise.ticketbookingsystem.repository.EventRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public void save(Event event) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(event);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
            }
        }
    }

    public void deleteById(Long id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Event event = session.get(Event.class, id);
                if (event != null) {
                    session.delete(event);
                } else {
                    System.out.println("Event with id " + id + " not found.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
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
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public Event findById(Long id) {
        Event event;
        try (Session session = sessionFactory.openSession()) {
            event = session.get(Event.class, id);
            if (event == null) {
                System.out.println("Event with id " + id + " not found.");
            }
        }
        return event;
    }

    public List<Event> findByCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Event WHERE category = :category", Event.class)
                        .setParameter("category", category)
                        .getResultList();
        }

    }

    public List<Event> getAllEvents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Event", Event.class).list();
        }
    }

    @Override
    public List<Event> findByDateRange(LocalDate startDate, LocalDate endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Event WHERE dateStart >= :startDate AND dateEnd <= :endDate", Event.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }
    }

    @Override
    public List<Event> findUpcomingEvents(LocalDate today) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Event WHERE dateStart >= :today", Event.class)
                    .setParameter("today", today)
                    .getResultList();
        }
    }
}
