package com.mywebapp.pizza.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatClass {

    @JsonProperty("dish_name")
    private String dishName;

    @JsonProperty("amount")
    private Integer amount;

    public StatClass(Dish dish, Integer amount) {
        this.dishName = dish.getName();
        this.amount = amount;
    }

    public StatClass() {
    }
}
