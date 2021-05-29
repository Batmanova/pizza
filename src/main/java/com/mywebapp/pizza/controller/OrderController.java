package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.dto.OrderDto;
import com.mywebapp.pizza.service.impl.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    /**
     *
     * @param orderService
     */

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @param orderDto
     * @return  ResponseEntity
     */

    @PostMapping("/add")
    public ResponseEntity<?> orderAdd(@RequestBody OrderDto orderDto) {
        orderService.create(OrderDto.builder().summ(orderDto.getSumm()).isReady(orderDto.getIsReady())
                .isActive(orderDto.getIsActive())
                .employeeId(orderDto.getEmployeeId())
                .name(orderDto.getName())
                .build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @return ResponseEntity
     */

    @GetMapping("/")
    public ResponseEntity<?> order() {
        return new ResponseEntity<>(orderService.get(), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return ResponseEntity
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.delete(
                OrderDto.builder()
                .id(id)
                .build()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param orderDto
     * @return ResponseEntity
     */

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto) {
        orderService.update(orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
