package com.mywebapp.pizza.service.impl;

import com.mywebapp.pizza.model.Shift;
import com.mywebapp.pizza.repository.ShiftRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;

    /**
     *
     * @param model
     */

    public void create(Shift model) {
        shiftRepository.save(model);
    }

    /**
     *
     * @param model
     */

    public void delete(Shift model) { shiftRepository.delete(model); }

    /**
     *
     * @return shifts
     */

    public List<Shift> get() { return shiftRepository.findAll(); }

    /**
     *
     * @param id
     * @return shift
     */

    public Optional<Shift> read(Long id) { return shiftRepository.findById(id); }

    /**
     *
     * @return active shifts
     */

    public List<Shift> getActive() { return shiftRepository.findAllByIsActiveTrue(); }

    /**
     *
     * @param model
     */

    public void update(Shift model) { shiftRepository.save(model); }
}
