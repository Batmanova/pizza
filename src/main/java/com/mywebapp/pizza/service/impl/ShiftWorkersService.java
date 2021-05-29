package com.mywebapp.pizza.service.impl;

import com.mywebapp.pizza.model.Shift;
import com.mywebapp.pizza.model.ShiftWorkers;
import com.mywebapp.pizza.repository.ShiftWorkersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftWorkersService {
    private final ShiftWorkersRepository shiftWorkersRepository;

    /**
     *
     * @param model
     */

    public void create(ShiftWorkers model) { shiftWorkersRepository.save(model); }

    /**
     *
     * @param model
     */

    public void delete(ShiftWorkers model) { shiftWorkersRepository.delete(model); }

    /**
     *
     * @return timetable
     */

    public List<ShiftWorkers> get() { return shiftWorkersRepository.findAll(); }

    /**
     *
     * @param shift
     * @return shift
     */

    public List<ShiftWorkers> getById(Shift shift) { return  shiftWorkersRepository.findAllByShiftWorkersPKShiftId(shift); }

    /**
     *
     * @param worker
     * @return shift-worker
     */

    public Optional<ShiftWorkers> getByPK (ShiftWorkers worker) { return shiftWorkersRepository.findByShiftWorkersPK(worker.getShiftWorkersPK()); }

    /**
     *
     * @param model
     */

    public void update(ShiftWorkers model) { shiftWorkersRepository.save(model); }
}
