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
@Table(name = "document")
public class Document implements Serializable {

    @Id
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employeeId;

    @Column(name = "passport")
    private String passport;

    @Column(name = "inila")
    private String inila;

    @Column(name = "id_military")
    private String idMilitary;

}
