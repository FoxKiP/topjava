package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

class Storage {
    private static final Logger log = getLogger(Storage.class);
    private final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger();

    Storage() {
        meals.put(count.incrementAndGet(), new Meal(count.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.put(count.incrementAndGet(), new Meal(count.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.put(count.incrementAndGet(), new Meal(count.get(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.put(count.incrementAndGet(), new Meal(count.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.put(count.incrementAndGet(), new Meal(count.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.put(count.incrementAndGet(), new Meal(count.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.put(count.incrementAndGet(), new Meal(count.get(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    void add(Meal meal) {
        int id = count.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
    }

    void delete(int id) {
        meals.remove(id);
    }

    void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    Meal getById(int id) {
        return meals.get(id);
    }

    List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}
