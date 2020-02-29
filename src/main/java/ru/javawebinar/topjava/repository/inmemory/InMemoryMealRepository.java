package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        Map<Integer, Meal> meals = computeIfAbsent(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id,int userId) {
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id,int userId) {
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return CollectionUtils.isEmpty(meals) ? Collections.emptyList() : meals.values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());

    }

    public List<Meal> filter(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAll(userId)
                .stream()
                .filter(meal -> DateTimeUtil.isBetweenInclusive(meal.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }

    private Map<Integer, Meal> computeIfAbsent(int userId) {
        return repository.computeIfAbsent(userId, ConcurrentHashMap::new);
    }
}

