package com.mywebapp.pizza.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor //automatic constructor generators
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChequeDTO {

    @JsonProperty("order_id")
    private int orderId;

    @JsonProperty("dish_id")
    private int dishId;

    @JsonProperty("amount")
    private Integer amount;

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChequeDTO object = (ChequeDTO) o;
        if (object.amount == this.amount && object.dishId == this.dishId && object.orderId == this.orderId) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, dishId, amount);
    }*/
}
