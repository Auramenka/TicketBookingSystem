package com.innowise.ticketbookingsystem.util;

import java.util.HashMap;
import java.util.Map;

public class BookingUtil {

    private static final Map<String, Object> bookingData = new HashMap<>();

    public static void put(String key, Object value) {
        bookingData.put(key, value);
    }

    public static Object get(String key) {
        return bookingData.get(key);
    }
}
