package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MemoryMealService;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MemoryMealService memoryService = MemoryMealService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Meal meal = new Meal(
                Integer.parseInt(req.getParameter("id")),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories"))
        );
        log.info("add");
        memoryService.add(meal);
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null) {
            log.info("forward to meals");
            List<Meal> meals = memoryService.getAll();
            req.setAttribute("mealsTo", MealsUtil.getTos(meals, 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
        else {
            if (action.equals("create")) {
                log.info("create");
                Meal meal = new Meal(LocalDateTime.now(), "", 0);
                forwardToMealEdit(meal, req, resp);
            }
            if (action.equals("update")) {
                int id = getId(req);
                log.info("update: " + id);
                Meal meal = memoryService.getById(id);
                forwardToMealEdit(meal, req, resp);
            }
            if (action.equals("delete")) {
                int id = getId(req);
                log.info("delete: " + id);
                memoryService.delete(id);
                resp.sendRedirect("meals");
            }
        }

    }

    private int getId(HttpServletRequest req) {
        String id = Objects.requireNonNull(req.getParameter("id"));
        return Integer.parseInt(id);
    }

    private void forwardToMealEdit(Meal meal, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
    }
}
