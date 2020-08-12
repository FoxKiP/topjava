package ru.javawebinar.topjava.util.annotation.datetimeformat;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.text.ParseException;

import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Locale;

public class TimeParser implements Formatter<Temporal> {
    @Override
    public LocalTime parse(String s, Locale locale) throws ParseException {
        return DateTimeUtil.parseLocalTime(s);
    }

    @Override
    public String print(Temporal temporal, Locale locale) {
        return temporal.toString();
    }
}
