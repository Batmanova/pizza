package com.mywebapp.pizza.repository;

import com.mywebapp.pizza.model.Shift;
import com.mywebapp.pizza.model.ShiftWorkers;
import com.mywebapp.pizza.model.ShiftWorkersPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftWorkersRepository extends JpaRepository<ShiftWorkers,Long> {
    List<ShiftWorkers> findAllByShiftWorkersPKShiftId(Shift shift);
    Optional<ShiftWorkers> findByShiftWorkersPK(ShiftWorkersPK pk);
}
