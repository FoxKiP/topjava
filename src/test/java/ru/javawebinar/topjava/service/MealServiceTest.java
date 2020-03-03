package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        assertMatch(service.get(MEAL1_ID, SecurityUtil.authUserId()), MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getAnotherId() {
        assertMatch(service.get(MEAL1_ID, 1), MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        service.delete(MEAL3_ID, SecurityUtil.authUserId());
        service.get(MEAL3_ID, SecurityUtil.authUserId());
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnotherId() {
        service.delete(MEAL3_ID, 1);
    }

    @Test
    public void getBetweenHalfOpen() {
        List<Meal> filteredMeals = service
                .getBetweenHalfOpen(MEAL3.getDate(), MEAL3.getDate(), SecurityUtil.authUserId());
        assertMatch(filteredMeals, MEAL3, MEAL2, MEAL1);
        assertMatch(service.getBetweenHalfOpen(null, null, SecurityUtil.authUserId()),
                MealTestData.getAll());
    }

    @Test
    public void getAll() {
       List<Meal> actual = service.getAll(SecurityUtil.authUserId());
       List<Meal> expected = MealTestData.getAll();
       assertMatch(actual, expected);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, SecurityUtil.authUserId());
        assertMatch(service.get(MEAL1_ID, SecurityUtil.authUserId()), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateAnotherId() {
        Meal updated = getUpdated();
        service.update(updated, SecurityUtil.authUserId());
        assertMatch(service.get(MEAL1_ID, 1), updated);
    }

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, SecurityUtil.authUserId());
        Integer id = created.getId();
        newMeal.setId(id);
        assertMatch(created, newMeal);
        assertMatch(service.get(id, SecurityUtil.authUserId()), newMeal);
    }
}