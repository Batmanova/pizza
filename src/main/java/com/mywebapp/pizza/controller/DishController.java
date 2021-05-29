package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.Dish;
import com.mywebapp.pizza.service.impl.DishService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DishController {

    private final DishService dishService;

    /**
     *
     * @param dishService
     */

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    /**
     *
     * @return ResponseEntity
     */

    @GetMapping("/dishes")
    public ResponseEntity<?> dish() {
        return new ResponseEntity<>(dishService.get(), HttpStatus.OK);
    }

    /**
     *
     * @param dishdto
     * @return ResponseEntity
     */

    @PostMapping("/create-dish")
    public ResponseEntity<?> add(@RequestBody Dish dishdto) {
        dishService.create(Dish.builder().name(dishdto.getName()).price(dishdto.getPrice()).build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return ResponseEntity
     */

    @DeleteMapping("dish/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        dishService.delete(dishService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
