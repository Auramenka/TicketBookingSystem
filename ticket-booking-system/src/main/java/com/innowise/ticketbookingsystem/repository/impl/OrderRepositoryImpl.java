package com.innowise.ticketbookingsystem.repository.impl;

import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.model.Order;
import com.innowise.ticketbookingsystem.model.OrderStatus;
import com.innowise.ticketbookingsystem.repository.OrderRepository;
import com.innowise.ticketbookingsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> results = session.createNativeQuery(
                            "SELECT e.name, s.date_start, s.time_start, s2.row_number, s2.seat_number, o.order_status " +
                                    "FROM orders o " +
                                    "JOIN events e ON o.event_id = e.id " +
                                    "JOIN booking b ON b.id = o.booking_id " +
                                    "JOIN seances s ON b.seance_id = s.id " +
                                    "JOIN seats s2 ON b.seat_id = s2.id " +
                                    "WHERE o.user_id = :userId")
                    .setParameter("userId", userId)
                    .getResultList();

            List<OrderDto> orders = new ArrayList<>();
            for (Object[] result : results) {
                OrderDto dto = new OrderDto();
                dto.setId(userId);
                dto.setEventName((String) result[0]);
                dto.setEventDate(((Date) result[1]).toLocalDate());
                dto.setEventTime(((Time) result[2]).toLocalTime());
                dto.setSeatInfo("Ряд: " + result[3] + " ; номер: " + result[4]);

                String statusValue = (String) result[5];
                dto.setOrderStatus(OrderStatus.valueOf(statusValue));
                orders.add(dto);
            }
            return orders;
        }
    }
}