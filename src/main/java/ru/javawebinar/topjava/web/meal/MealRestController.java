package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.annotation.datetimeformat.FormatDateTime;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {

    final static String REST_URL = "/rest/meals";
    private final static String ID = "/{id}";

    @Override
    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(ID)
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal,@PathVariable int id) {
        super.update(meal, id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + ID)
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @GetMapping("/between")
    public List<MealTo> getBetween(@FormatDateTime(iso = FormatDateTime.ISO.DATA)
                                   LocalDate startDate,
                                   @FormatDateTime(iso = FormatDateTime.ISO.TIME)
                                   LocalTime startTime,
                                   @FormatDateTime(iso = FormatDateTime.ISO.DATA)
                                   LocalDate endDate,
                                   @FormatDateTime(iso = FormatDateTime.ISO.TIME)
                                   LocalTime endTime) {

        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}