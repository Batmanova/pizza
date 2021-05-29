package com.mywebapp.pizza.service.impl;

import com.mywebapp.pizza.model.Employee;
import com.mywebapp.pizza.model.Order;
import com.mywebapp.pizza.model.dto.OrderDto;
import com.mywebapp.pizza.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    /**
     *
     * @param model
     */

    public void create(OrderDto model) { orderRepository.save(toModel(model)); }

    /**
     *
     * @param model
     */

    public void delete(OrderDto model) { orderRepository.delete(toModel(model)); }

    /**
     *
     * @return orderDTOs
     */

    public List<OrderDto> get() {
      return orderRepository.findAll()
               .stream() //collection's work in sql style
               .map(this::orderDto) //change every element from orderrepo
               .collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return order
     */

    public Order getOne(Long id) { return orderRepository.getOne(id); }

    /**
     *
     * @param model
     */

    public void update(OrderDto model) { orderRepository.save(toModel(model)); }

    /**
     *
     * @param model
     */

    public void update(Order model) { orderRepository.save(model); }

    /**
     *
     * @param order
     * @return orderDTO
     */

    private OrderDto orderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .name(order.getName())
                .summ(order.getSumm())
                .isActive(order.getIsActive())
                .employeeId(order.getEmployeeId().getId())
                .isReady(order.getIsReady())
                .build();

    }

    /**
     *
     * @param orderDto
     * @return order
     */

    private Order toModel(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .isActive(orderDto.getIsActive())
                .name(orderDto.getName())
                .isReady(orderDto.getIsReady())
                .summ(orderDto.getSumm())
                .employeeId(
                        Employee.builder()
                        .id(orderDto.getEmployeeId())
                        .build()
                )
                .build();

    }

}
