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
@Table(name = "cheque")
public class Cheque implements Serializable {

    @EmbeddedId
    private ChequePK chequeId;

    @Column(name = "amount")
    private Integer amount;
}
