package com.innowise.ticketbookingsystem.util;

import com.innowise.ticketbookingsystem.dto.EventDto;
import com.innowise.ticketbookingsystem.dto.OrderDto;
import com.innowise.ticketbookingsystem.dto.SeanceDto;
import com.innowise.ticketbookingsystem.dto.UserDto;
import com.innowise.ticketbookingsystem.model.enums.Category;
import com.innowise.ticketbookingsystem.model.enums.OrderStatus;
import com.innowise.ticketbookingsystem.service.EventService;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class MapperUtility {

    public static EventDto mapRequestToEventDto(HttpServletRequest req) {
        EventDto eventDto = new EventDto();
        eventDto.setName(req.getParameter("name"));
        eventDto.setDescription(req.getParameter("description"));
        eventDto.setDateStart(LocalDate.parse(req.getParameter("dateStart")));
        eventDto.setDateEnd(LocalDate.parse(req.getParameter("dateEnd")));
        eventDto.setCategory(Category.valueOf(req.getParameter("category")));
        eventDto.setPhoto(req.getParameter("photo"));
        return eventDto;
    }

    public static UserDto mapRequestToUserDto(HttpServletRequest req, UserDto existingUserDto) {
        Long id = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        existingUserDto.setId(id);
        existingUserDto.setUsername(username);
        existingUserDto.setEmail(email);

        return existingUserDto;
    }

    public static List<EventDto> mapParametersToEvents(String categoryParam, String startDateParam, String endDateParam, EventService eventService) {
        List<EventDto> events;

        if (Objects.nonNull(startDateParam) && Objects.nonNull(endDateParam)) {
            LocalDate startDate = LocalDate.parse(startDateParam);
            LocalDate endDate = LocalDate.parse(endDateParam);
            events = eventService.getEventsByDateRange(startDate, endDate);
        } else if (Objects.nonNull(categoryParam)) {
            Category category = Category.valueOf(categoryParam.toUpperCase());
            events = eventService.getEventsByCategory(category);
        } else {
            events = eventService.getEvents();
        }
        return events;
    }

    public static OrderDto mapToOrderDto(HttpServletRequest req, String action, Long userId) {
        Long eventId = (Long) BookingUtil.get("eventId");
        Long bookingId = (Long) BookingUtil.get("bookingId");

        OrderStatus status;
        if ("buy".equals(action)) {
            status = OrderStatus.PURCHASE;
        } else if ("reserve".equals(action)) {
            status = OrderStatus.BOOKING;
        } else {
            return null;
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderStatus(status);
        orderDto.setUserId(userId);
        orderDto.setEventId(eventId);
        orderDto.setBookingId(bookingId);

        return orderDto;
    }

    public static UserDto mapToUserDto(HttpServletRequest req) {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setPassword(password);

        return userDto;
    }

    public static SeanceDto mapRequestToSeanceDto(HttpServletRequest req) {
        LocalDate dateStart = LocalDate.parse(req.getParameter("dateStart"));
        LocalTime timeStart = LocalTime.parse(req.getParameter("timeStart"));
        Long eventId = Long.parseLong(req.getParameter("eventId"));

        SeanceDto seanceDto = new SeanceDto();
        seanceDto.setDateStart(dateStart);
        seanceDto.setTimeStart(timeStart);
        seanceDto.setEventId(eventId);

        return seanceDto;
    }

    public static OrderDto createOrderDto(Object[] result, Long userId) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(userId);
        orderDto.setEventName((String) result[0]);
        orderDto.setEventDate(((Date) result[1]).toLocalDate());
        orderDto.setEventTime(((Time) result[2]).toLocalTime());
        orderDto.setSeatInfo("Ряд: " + result[3] + " ; номер: " + result[4]);

        String statusValue = (String) result[5];
        orderDto.setOrderStatus(OrderStatus.valueOf(statusValue));
        return orderDto;
    }
}
