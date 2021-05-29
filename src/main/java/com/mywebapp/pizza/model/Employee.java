package com.mywebapp.pizza.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonProperty("fc_s")
    @Column(name = "fc_s")
    private String fcS;

    @JsonProperty("job_name")
    @Column(name = "job_name")
    private String jobName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;
}
