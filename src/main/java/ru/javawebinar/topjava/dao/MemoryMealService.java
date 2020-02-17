package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MemoryMealService implements MealService {
    private final Storage storage = new Storage();
    private static MemoryMealService instance;

    private MemoryMealService(){}

    public static MemoryMealService getInstance() {
        if(instance == null) instance = new MemoryMealService();
        return instance;
    }

    @Override
    public void add(Meal meal) {
        storage.add(meal);
    }

    @Override
    public void delete(int id) {
        storage.delete(id);
    }

    @Override
    public List<Meal> getAll() {
        return storage.getAll();
    }

    @Override
    public Meal getById(int id) {
        return storage.getById(id);
    }
}
