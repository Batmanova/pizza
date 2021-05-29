package com.mywebapp.pizza.service.impl;

import com.mywebapp.pizza.model.Employee;
import com.mywebapp.pizza.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl{
    private final EmployeeRepository employeeRepository;

    /**
     *
     * @param model
     */

    public void create(Employee model) {
        employeeRepository.save(model);
    }

    /**
     *
     * @param model
     */

    public void delete(Employee model) {
        employeeRepository.delete(model);
    }

    /**
     *
     * @return employees
     */

    public List<Employee> get() {
        return employeeRepository.findAll();
    }

    /**
     *
     * @param employee
     */

    public void update(Employee employee) { employeeRepository.save(employee); }

    /**
     *
     * @param password
     * @param phone
     * @return employee
     */

    public Optional<Employee> findByPasswordAndPhone(String password, String phone) {
        return employeeRepository.findByPasswordAndPhone(password,phone);
    }

    /**
     *
     * @param id
     * @return employee
     */

    public Optional<Employee> findOneById(Long id) {
        return employeeRepository.findOneById(id);
  }

}
