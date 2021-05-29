package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.Employee;
import com.mywebapp.pizza.model.Shift;
import com.mywebapp.pizza.model.ShiftWorkers;
import com.mywebapp.pizza.model.ShiftWorkersPK;
import com.mywebapp.pizza.model.dto.ShiftWorkersDTO;
import com.mywebapp.pizza.service.impl.EmployeeServiceImpl;
import com.mywebapp.pizza.service.impl.ShiftService;
import com.mywebapp.pizza.service.impl.ShiftWorkersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shift-workers")
public class ShiftWorkersController {

    private final ShiftWorkersService shiftWorkersService;
    private final ShiftService shiftService;
    private final EmployeeServiceImpl employeeService;

    /**
     *
     * @param shiftWorkersService
     * @param service
     * @param employeeService
     */

    public ShiftWorkersController(ShiftWorkersService shiftWorkersService, ShiftService service, EmployeeServiceImpl employeeService) {
        this.shiftWorkersService = shiftWorkersService;
        this.shiftService = service;
        this.employeeService = employeeService;
    }

    /**
     *
     * @param shiftWorkersPKList
     * @return ResponseEntity
     */

    @PostMapping("/add")
    public ResponseEntity<?> addWorkers(@RequestBody List<ShiftWorkersPK> shiftWorkersPKList) {
        shiftWorkersPKList.forEach(shiftWorkersPK ->  shiftService.create(shiftWorkersPK.getShiftId()));
        shiftWorkersPKList.forEach(shiftWorkersPK -> {
          ShiftWorkers shiftWorkers =  ShiftWorkers.builder()
                    .shiftWorkersPK(shiftWorkersPK)
                    .build();
            shiftWorkersService.create(shiftWorkers);});
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param shiftWorkers
     * @return ResponseEntity
     */

    @PutMapping("/update")
    public ResponseEntity<?> updateWorkers(@RequestBody ShiftWorkersPK shiftWorkers) {
        shiftWorkersService.update(ShiftWorkers.builder()
                .shiftWorkersPK(shiftWorkers).build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param shiftWorkers
     * @return ResponseEntity
     */

    @PostMapping("/shifts")
    public ResponseEntity<?> shifts(@RequestBody List<ShiftWorkersDTO> shiftWorkers) {
        for (ShiftWorkersDTO worker : shiftWorkers) {
            if (employeeService.findOneById(worker.employee).isPresent() & shiftService.read(worker.shift).isPresent()) {
                ShiftWorkers model = new ShiftWorkers(new ShiftWorkersPK(employeeService.findOneById(worker.employee).get(), shiftService.read(worker.shift).get()));
                if (shiftWorkersService.getByPK(model).isPresent()) {
                    shiftWorkersService.update(model);
                } else {
                    shiftWorkersService.create(model);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @return ResponseEntity
     */
    //так как смены генерируются на фронте нет смысла тест
    @GetMapping("/generate")
    public ResponseEntity<?> generate() {
        List<Shift> active = shiftService.getActive();
        HashMap<Shift, Shift> shifts = new HashMap<>();
        List<ShiftWorkers> oldShifts = new ArrayList<>();
        for (Shift shift : active) {
            oldShifts.addAll(shiftWorkersService.getById(shift));
            shift.setIsActive(false);
            shiftService.update(shift);
            Shift tmp = Shift.builder().isActive(true).dates(shift.getDates().plusDays(7)).isSecond(shift.getIsSecond()).build();
            shifts.put(shift, tmp);
            shiftService.create(tmp);
        }
        for (ShiftWorkers shiftWorkers : oldShifts) {
            ShiftWorkersPK tmpPK = new ShiftWorkersPK(shiftWorkers.getShiftWorkersPK().getEmployeeId(), shifts.get(shiftWorkers.getShiftWorkersPK().getShiftId()));
            ShiftWorkers tmpModel = new ShiftWorkers(tmpPK);
            shiftWorkersService.create(tmpModel);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @return ResponseEntity
     */

    @GetMapping("/")
    public ResponseEntity<?> getAllShiftWorkers() { return new ResponseEntity<>(shiftWorkersService.get(), HttpStatus.OK); }

    /**
     *
     * @param id
     * @return ResponseEntity
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllShiftWorkers(@PathVariable(name = "id") Long id) {
        Optional<Shift> opt = shiftService.read(id);
        List<Employee> employees;
        if (opt.isPresent()) {
            List<ShiftWorkersPK> pks = shiftWorkersService.getById(opt.get()).stream().map(ShiftWorkers::getShiftWorkersPK).collect(Collectors.toList());
            employees = pks.stream().map(ShiftWorkersPK::getEmployeeId).collect(Collectors.toList());
        } else {
            employees = new ArrayList<>();
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     *
     * @return ResponseEntity
     */

    @GetMapping("/shifts")
    public ResponseEntity<?> getAllShifts() {
        List<Shift> shifts = shiftService.get();
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param id2
     * @return ResponseEntity
     */

    @DeleteMapping("/delete/{id}/{id2}")
    public ResponseEntity<?> deleteShiftWorkers(@PathVariable Long id, @PathVariable Long id2) {
        try {
            shiftWorkersService.delete(ShiftWorkers.builder()
                    .shiftWorkersPK(ShiftWorkersPK.builder()
                            .shiftId(Shift.builder()
                                    .id(id2)
                                    .build())
                            .employeeId(Employee.builder()
                                    .id(id)
                                    .build())
                            .build())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
