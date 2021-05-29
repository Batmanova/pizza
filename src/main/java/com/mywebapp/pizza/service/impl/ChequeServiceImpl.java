package com.mywebapp.pizza.service.impl;

import com.mywebapp.pizza.model.Cheque;
import com.mywebapp.pizza.model.ChequePK;
import com.mywebapp.pizza.repository.ChequeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl{
    private final ChequeRepository chequeRepository;

    /**
     *
     * @param model
     */

    public void create(Cheque model) { chequeRepository.save(model); }

    /**
     *
     * @param model
     */

    public void delete(Cheque model) { chequeRepository.delete(model); }

    /**
     *
     * @return cheques
     */

    public List<Cheque> get() {
        return chequeRepository.findAll();
    }

    /**
     *
     * @param cheque
     */

    public void update(Cheque cheque) { chequeRepository.save(cheque); }

    /**
     *
     * @param cheque
     * @return cheques
     */

    public List<Cheque> findAllByOrderId(Cheque cheque) {
       return chequeRepository.findAllByChequeIdOrderId(cheque.getChequeId().getOrderId());
    }

    /**
     *
     * @param cheque
     * @return cheque
     */

    public Cheque findAllByChequeIdAndOrderId(Cheque cheque) {
        return chequeRepository.findByChequeId(new ChequePK(cheque.getChequeId().getDishId(), cheque.getChequeId().getOrderId()));
    }

    /**
     *
     * @return cheques
     */

    public List<Cheque> findAll() { return chequeRepository.findAll(); }

}
