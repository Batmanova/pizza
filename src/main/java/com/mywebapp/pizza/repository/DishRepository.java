package com.mywebapp.pizza.repository;

import com.mywebapp.pizza.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> { Dish findAllById(Long id); }