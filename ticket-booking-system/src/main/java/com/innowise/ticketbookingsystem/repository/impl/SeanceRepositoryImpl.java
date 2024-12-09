package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Event;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.repository.SeanceRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SeanceRepositoryImpl implements SeanceRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(Seance seance) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(seance);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Seance seance = session.get(Seance.class, id);
                if (seance != null) {
                    session.delete(seance);
                } else {
                    System.out.println("Seance with id " + id + " not found.");
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public Seance findById(Long seanceId) {
        Seance seance;
        try (Session session = sessionFactory.openSession()) {
            seance = session.get(Seance.class, seanceId);
            if (seance == null) {
                System.out.println("Seance with id " + seanceId + " not found.");
            }
        }
        return seance;
    }

    public List<Seance> getAllSeances() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Seance ", Seance.class).list();
        }
    }

    public List<Seance> getSeancesByEventId(Long eventId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Seance where event.id = :eventId", Seance.class)
                    .setParameter("eventId", eventId)
                    .list();
        }
    }
}
