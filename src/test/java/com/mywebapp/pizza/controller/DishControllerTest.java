package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.Dish;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DishControllerTest {

    @org.junit.jupiter.api.Test
    void test() {
        Dish dish = new Dish(new Long(-1), "cola", new Double(150));
        long id = dish.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Dish> request = new HttpEntity<>(dish);
        ResponseEntity<Dish> sent = restTemplate.exchange("http://localhost:8090/create-dish", HttpMethod.POST, request, Dish.class);
        ResponseEntity<Dish[]> response = restTemplate.getForEntity("http://localhost:8090/dishes", Dish[].class);
        Dish[] dishes = response.getBody();
        boolean notFound = true;
        for (Dish lt : dishes) {
            if (lt.getName().equals("cola")) {
                Assert.assertEquals(dish.getPrice(), lt.getPrice());
                notFound = false;
                id = lt.getId();
            }
        }
        if (notFound) {
            Assert.assertEquals(dish.getId(), dishes[0].getId()); //всегда провалится, вызывается если в список не добавится кола
            notFound = false;
        }
        String url = "http://127.0.0.1:8090/dish/delete/" + Long.toString(id);
        restTemplate.delete(url);
        response = restTemplate.getForEntity("http://localhost:8090/dishes", Dish[].class);
        dishes = response.getBody();
        for (Dish lt : dishes) {
            if (lt.getName() == "cola") {
                Assert.assertEquals(new Long(-1), lt.getId()); //если после удаления кола осталась
                notFound = true;
            }
        }
        if (notFound) {
            Assert.assertEquals(-1, -1); //если колы уже нет
        }
    }

}