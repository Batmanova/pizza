package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.*;
import com.mywebapp.pizza.model.dto.ChequeDTO;
import com.mywebapp.pizza.service.impl.ChequeServiceImpl;
import com.mywebapp.pizza.service.impl.DishService;
import com.mywebapp.pizza.service.impl.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChequeController {
    private final ChequeServiceImpl chequeService;
    private final OrderService orderService;
    private final DishService dishService;

    /**
     *
     * @param chequeService
     * @param orderService
     * @param dishService
     */

    public ChequeController(ChequeServiceImpl chequeService, OrderService orderService, DishService dishService) {
        this.chequeService = chequeService;
        this.orderService = orderService;
        this.dishService = dishService;
    }

    /**
     *
     * @param id
     * @return ResponseEntity
     */

    @GetMapping("/cheque/{id}")
    public ResponseEntity<?> getCheque(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(chequeService.findAllByOrderId(
                Cheque.builder()
                .chequeId(ChequePK
                .builder()
                .orderId(
                        Order.builder()
                        .id(id)
                        .build()
                )
                .build())
                .build()

        ), HttpStatus.OK);
    }

    /**
     *
     * @param chequeList
     * @return ResponseEntity
     */

    @PostMapping("/cheque/add")
    public ResponseEntity<?> add(@RequestBody List<ChequeDTO> chequeList) {
        for (ChequeDTO cheque : chequeList) {
            Dish dish = dishService.getById(Long.parseLong(Integer.toString(cheque.getDishId())));
            Order order = orderService.getOne(Long.parseLong(Integer.toString(cheque.getOrderId())));
            Cheque chequeM = new Cheque(new ChequePK(dish, order), cheque.getAmount());
            if (chequeService.findAllByChequeIdAndOrderId(chequeM) != null) {
                order.setSumm(order.getSumm() - dish.getPrice()*chequeService.findAllByChequeIdAndOrderId(chequeM).getAmount() + dish.getPrice()*cheque.getAmount());
                chequeService.update(chequeM);
            } else {
                if (cheque.getAmount() != 0) {
                    order.setSumm(order.getSumm() +  dish.getPrice()*cheque.getAmount());
                    orderService.update(order);
                    chequeService.create(chequeM);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @return ResponseEntity
     */

    @GetMapping("/stat")
    public ResponseEntity<?> getStat() {
        List<Cheque> cheques = chequeService.findAll();
        List<StatClass> response = new ArrayList<>();
        for (Cheque cheque : cheques) {
            response.add(new StatClass(cheque.getChequeId().getDishId(), cheque.getAmount()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
