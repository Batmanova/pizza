package com.mywebapp.pizza.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor //automatic constructor generators
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Long id;
    private Double summ;
    private Boolean isReady;
    private Boolean isActive;
    private String name;
    private Long employeeId;
}
