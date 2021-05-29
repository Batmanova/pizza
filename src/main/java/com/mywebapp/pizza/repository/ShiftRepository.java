package com.mywebapp.pizza.repository;

import com.mywebapp.pizza.model.Shift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,Long> {
     Optional<Shift> findById(Long id);
     List<Shift> findAllByIsActiveTrue();
}
