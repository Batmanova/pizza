package com.mywebapp.pizza.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor //automatic constructor generators
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("isSecond")
    private boolean is_second;

    @JsonProperty("dates")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @JsonProperty("isActive")
    private boolean isActive;
}
