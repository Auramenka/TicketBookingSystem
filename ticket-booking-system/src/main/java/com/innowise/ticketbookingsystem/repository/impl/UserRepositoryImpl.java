package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.model.User;
import com.innowise.ticketbookingsystem.repository.UserRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
            }
        }
    }

    public void update(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(user);
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
                User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
            }
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    public User findById(Long id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
            if (user == null) {
                System.out.println("User with id " + id + " not found.");
            }
        }
        return user;
    }
}
