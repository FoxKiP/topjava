package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

public class MealRestController {
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        return MealsUtil.getTos(new ArrayList<>(service.getAll(SecurityUtil.authUserId()))
                , SecurityUtil.authUserCaloriesPerDay());
    }

    public void update(Meal meal, int id) {
        assureIdConsistent(meal, id);
        service.update(meal, SecurityUtil.authUserId());
    }

    public List<MealTo> filter(String startD, String startT, String endD, String endT) {
        LocalDate startDate = startD == null || startD.isEmpty() ? LocalDate.MIN : LocalDate.parse(startD);
        LocalTime startTime = startT == null || startT.isEmpty() ? LocalTime.MIN : LocalTime.parse(startT);
        LocalDate endDate = endD == null || endD.isEmpty() ? LocalDate.MAX : LocalDate.parse(endD);
        LocalTime endTime = endT == null || endT.isEmpty() ? LocalTime.MAX : LocalTime.parse(endT);
        return MealsUtil.getTos(service.filter(startDate.atTime(startTime), endDate.atTime(endTime)
                , SecurityUtil.authUserId())
                , SecurityUtil.authUserCaloriesPerDay());
    }
}