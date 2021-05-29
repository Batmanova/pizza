package com.mywebapp.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shift_workers")
public class ShiftWorkers implements Serializable {
    @EmbeddedId
    private ShiftWorkersPK shiftWorkersPK;
}
