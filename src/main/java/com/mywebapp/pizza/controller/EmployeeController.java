package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.Employee;
import com.mywebapp.pizza.service.impl.EmployeeServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    /**
     *
     * @param employeeService
     */

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    /**
     *
     * @return ResponseEntity
     */

    @GetMapping("/")
    public List<Employee> employeeList() {
        return employeeService.get();
    }

    /**
     *
     * @param id
     * @return ResponseEntity
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(name = "id") Long id)  {
        return new ResponseEntity<>(employeeService.findOneById(id), HttpStatus.OK);
    }

    /**
     *
     * @param unknown
     * @return ResponseEntity
     */

    @PostMapping("/security")
    public ResponseEntity<?> checkUser(@RequestBody Employee unknown) {

        AtomicInteger status = new AtomicInteger();
        List<Employee> employeeList = employeeService.get();
        employeeList.stream()
                .filter(employee -> employee.getPhone().equals(unknown.getPhone())
                        && employee.getPassword().equals(unknown.getPassword()))
                .findAny()
                .ifPresentOrElse( //если есть, делаем первый параметр, иначе второй
                        o -> status.set(200),
                        Optional::empty //:: - для вызова метода класса, т.е. Optional.empty() - красивый null
                );
       if(status.get()==200) {
           Optional <Employee> employeeOptional =
                   employeeService.findByPasswordAndPhone(unknown.getPassword(),unknown.getPhone());

           Employee employee = null;
           if(employeeOptional.isPresent()) { //check if Optional<User> not null
               employee = employeeOptional.get();
           }
           return new ResponseEntity<>(employee, HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       }
    }

    /**
     *
     * @param unknown
     * @return ResponseEntity
     */

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Employee unknown) {
        employeeService.create(Employee.builder().fcS(unknown.getFcS()).jobName("cashier").phone(unknown.getPhone()).password(unknown.getPassword()).build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param employee
     * @return ResponseEntity
     */

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return ResponseEntity
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (employeeService.findOneById(id).isPresent()) {
            employeeService.delete(employeeService.findOneById(id).get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
