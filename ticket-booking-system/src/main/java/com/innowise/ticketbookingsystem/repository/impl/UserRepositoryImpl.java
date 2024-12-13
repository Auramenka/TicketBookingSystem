package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.exceptions.RollbackException;
import com.innowise.ticketbookingsystem.exceptions.UserNotFoundException;
import com.innowise.ticketbookingsystem.model.enums.Role;
import com.innowise.ticketbookingsystem.model.User;
import com.innowise.ticketbookingsystem.repository.UserRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private static final String GET_ALL_USERS_BY_ROLE = "FROM User WHERE role = :role";
    private static final String GET_USER_BY_USERNAME = "FROM User WHERE username = :username";
    private static final String GET_USER_BY_EMAIL = "FROM User WHERE email = :email";

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Error while saving user: {}", user, e);
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
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Transaction rolled back while updating user: {}", user, e);
            }
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);
                if (Objects.nonNull(user) ) {
                    session.delete(user);
                }
                transaction.commit();
            } catch (RollbackException e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                log.error("Transaction rolled back while deleting user with id: {}", id, e);
            }
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_ALL_USERS_BY_ROLE, User.class)
                    .setParameter("role", Role.USER)
                    .list();
        }
    }

    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_USER_BY_USERNAME, User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_USER_BY_EMAIL, User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    public User findById(Long id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(User.class, id);
            if (Objects.isNull(user)) {
                log.error("User with id " + id + " not found.");
                throw new UserNotFoundException("User with id " + id + " not found.");
            }
        }
        return user;
    }
}