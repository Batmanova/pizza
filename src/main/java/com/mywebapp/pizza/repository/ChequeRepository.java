package com.mywebapp.pizza.repository;

import com.mywebapp.pizza.model.Cheque;
import com.mywebapp.pizza.model.ChequePK;
import com.mywebapp.pizza.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque,ChequePK> {
    List<Cheque> findAllByChequeIdOrderId(Order id);
    Cheque findByChequeId(ChequePK chequePK);
}
