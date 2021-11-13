package com.iliadigital.controledeponto.utils;

import java.time.LocalDateTime;

public class DateHelper {
    public static LocalDateTime firstHourOfDay(LocalDateTime dateTime) {
        return dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime lastHourOfDay(LocalDateTime dateTime) {
        return dateTime.withHour(23).withMinute(59).withSecond(59);
    }
}
