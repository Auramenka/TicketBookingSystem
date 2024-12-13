package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.exceptions.RollbackException;
import com.innowise.ticketbookingsystem.model.Order;
import com.innowise.ticketbookingsystem.repository.OrderRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import com.innowise.ticketbookingsystem.util.MapperUtility;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private static final String FIND_ORDERS_BY_USER_ID =
            "SELECT e.name, s.date_start, s.time_start, s2.row_number, s2.seat_number, o.order_status " +
                    "FROM orders o " +
                    "JOIN events e ON o.event_id = e.id " +
                    "JOIN booking b ON b.id = o.booking_id " +
                    "JOIN seances s ON b.seance_id = s.id " +
                    "JOIN seats s2 ON b.seat_id = s2.id " +
                    "WHERE o.user_id = :userId";
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (RollbackException e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            log.error("Failed to save order: {}", order, e);
        }
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> results = session.createNativeQuery(FIND_ORDERS_BY_USER_ID)
                    .setParameter("userId", userId)
                    .getResultList();

            return results.stream()
                    .map(result -> MapperUtility.createOrderDto(result, userId))
                    .collect(Collectors.toList());
        }
    }
}