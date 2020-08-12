package ru.javawebinar.topjava.util.annotation.datetimeformat;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface FormatDateTime {

    FormatDateTime.ISO iso() default ISO.NONE;

    public static enum ISO {
        DATA,
        TIME,
        NONE;

        private ISO() {

        }
    }
}
