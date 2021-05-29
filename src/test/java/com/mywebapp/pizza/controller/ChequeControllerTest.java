package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.Cheque;
import com.mywebapp.pizza.model.dto.ChequeDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ChequeControllerTest {

    @Test
    void test() {
        ChequeDTO cheque = new ChequeDTO(4,0,2);
        List<ChequeDTO> cheques = new ArrayList<>();
        cheques.add(cheque);
        ChequeDTO[] chequeDTOS = cheques.toArray(new ChequeDTO[0]);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ChequeDTO[]> request = new HttpEntity<>(chequeDTOS);
        ResponseEntity<ChequeDTO[]> sent = restTemplate.exchange("http://localhost:8090/cheque/add", HttpMethod.POST, request, ChequeDTO[].class);
        ResponseEntity<Cheque[]> response = restTemplate.getForEntity("http://localhost:8090/cheque/4", Cheque[].class);
        Cheque[] chequeDTO = response.getBody();
        assertEquals(cheque.getAmount(), chequeDTO[0].getAmount());
        assertEquals(cheque.getDishId(), chequeDTO[0].getChequeId().getDishId().getId());
        assertEquals(cheque.getOrderId(), chequeDTO[0].getChequeId().getOrderId().getId());
    }
}