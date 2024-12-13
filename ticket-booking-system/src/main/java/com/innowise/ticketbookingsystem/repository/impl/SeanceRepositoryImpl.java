package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.exceptions.RollbackException;
import com.innowise.ticketbookingsystem.exceptions.SeanceNotFoundException;
import com.innowise.ticketbookingsystem.model.Seance;
import com.innowise.ticketbookingsystem.repository.SeanceRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

@Slf4j
public class SeanceRepositoryImpl implements SeanceRepository {

    private static final String GET_ALL_SEANCES = "from Seance";
    private static final String GET_SEANCES_BY_EVENT_ID = "from Seance where event.id = :eventId";

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(Seance seance) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(seance);
                transaction.commit();
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Error while saving seance: {}", e.getMessage(), e);
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
                if (Objects.nonNull(seance)) {
                    session.delete(seance);
                } else {
                    log.error("Seance with id " + id + " not found.");
                    throw new SeanceNotFoundException("Seance with id " + id + " not found.");
                }
                transaction.commit();
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Error while deleting seance with id {}: {}", id, e.getMessage(), e);
            }
        }
    }

    public Seance findById(Long seanceId) {
        Seance seance;
        try (Session session = sessionFactory.openSession()) {
            seance = session.get(Seance.class, seanceId);
            if (Objects.isNull(seance)) {
                log.error("Seance with id " + seanceId + " not found.");
                throw new SeanceNotFoundException("Seance with id " + seanceId + " not found.");
            }
        }
        return seance;
    }

    public List<Seance> getAllSeances() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_ALL_SEANCES, Seance.class).list();
        }
    }

    public List<Seance> getSeancesByEventId(Long eventId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_SEANCES_BY_EVENT_ID, Seance.class)
                    .setParameter("eventId", eventId)
                    .list();
        }
    }
}