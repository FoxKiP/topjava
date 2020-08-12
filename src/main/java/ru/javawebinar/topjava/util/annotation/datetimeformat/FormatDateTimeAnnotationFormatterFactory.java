package ru.javawebinar.topjava.util.annotation.datetimeformat;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.*;


public class FormatDateTimeAnnotationFormatterFactory implements AnnotationFormatterFactory<FormatDateTime> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<>(Arrays.asList(LocalDate.class, LocalTime.class));
    }

    @Override
    public Printer<?> getPrinter(FormatDateTime formatDateTime, Class<?> aClass) {
        return getFormatter(formatDateTime);
    }

    @Override
    public Parser<?> getParser(FormatDateTime formatDateTime, Class<?> aClass) {
        return getFormatter(formatDateTime);
    }

    private Formatter<Temporal> getFormatter(FormatDateTime formatDateTime) {
        FormatDateTime.ISO iso = formatDateTime.iso();
        if(iso.equals(FormatDateTime.ISO.DATA)) {
            return new DataParser();
        } else if(iso.equals(FormatDateTime.ISO.TIME)) {
            return new TimeParser();
        } else throw new IllegalArgumentException();
    }
}
