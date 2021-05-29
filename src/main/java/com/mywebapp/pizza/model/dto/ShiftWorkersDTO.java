package com.mywebapp.pizza.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor //automatic constructor generators
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftWorkersDTO {

    @JsonProperty("employee_id")
    public Long employee;

    @JsonProperty("shift_id")
    public Long shift;
}
