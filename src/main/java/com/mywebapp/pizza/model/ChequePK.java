package com.mywebapp.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable //part of another entity
public class ChequePK implements Serializable {

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private Dish dishId;


    @OneToOne(cascade= CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    private Order orderId;
}
