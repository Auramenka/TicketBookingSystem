package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.repository.SeanceRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SeanceRepositoryImpl implements SeanceRepository {

    public void save(Seance seance) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(seance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }

    public List<Seance> getAllSeances() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Seance ", Seance.class).list();
        }
    }

    @Override
    public List<Seance> getSeancesByEventId(Long eventId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Seance where event.id = :eventId", Seance.class)
                    .setParameter("eventId", eventId)
                    .list();
        }
    }
}
