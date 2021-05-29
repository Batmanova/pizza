package com.mywebapp.pizza.repository;

import com.mywebapp.pizza.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByPasswordAndPhone(String password, String phone);
    Optional<Employee> findOneById(Long id);
}

