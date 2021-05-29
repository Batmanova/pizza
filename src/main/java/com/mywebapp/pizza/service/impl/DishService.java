package com.mywebapp.pizza.service.impl;

import com.mywebapp.pizza.model.Dish;
import com.mywebapp.pizza.repository.DishRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    /**
     *
     * @param dishRepository
     */

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    /**
     *
     * @return dishes
     */

    public List<Dish> get() {
        return dishRepository.findAll();
    }

    /**
     *
     * @param id
     * @return dish
     */

    public Dish getById(Long id) { return dishRepository.findAllById(id); }

    /**
     *
     * @param dish
     */

    public void create(Dish dish) { dishRepository.save(dish); }

    /**
     *
     * @param dish
     */

    public void delete(Dish dish) { dishRepository.delete(dish);}
}
