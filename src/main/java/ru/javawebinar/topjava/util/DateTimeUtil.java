package ru.javawebinar.topjava.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public static <T extends Comparable> boolean isBetweenInclusive(T dateOrTime, T start, T end) {
        return dateOrTime.compareTo(start) >= 0 && dateOrTime.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static String toString(Date date) {
        return DATE_FORMATTER.format(date);
    }
}

