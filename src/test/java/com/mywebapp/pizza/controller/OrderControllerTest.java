package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.Order;
import com.mywebapp.pizza.model.dto.OrderDto;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerTest {

    @org.junit.jupiter.api.Test
    void test() {
        OrderDto order = new OrderDto(new Long(-1), new Double(0), false, true, "ABCD", new Long(0));
        long id = order.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrderDto> request = new HttpEntity<>(order);
        ResponseEntity<OrderDto> sent = restTemplate.exchange("http://localhost:8090/orders/add", HttpMethod.POST, request, OrderDto.class);
        ResponseEntity<OrderDto[]> response = restTemplate.getForEntity("http://localhost:8090/orders/", OrderDto[].class);
        OrderDto[] orderDtos = response.getBody();
        boolean notFound = true;
        for (OrderDto lt : orderDtos) {
            if (lt.getName().equals("ABCD")) {
                Assert.assertEquals(order.getEmployeeId(), lt.getEmployeeId());
                Assert.assertEquals(order.getName(), lt.getName());
                Assert.assertEquals(order.getSumm(), lt.getSumm());
                Assert.assertEquals(order.getSumm(), lt.getSumm());
                Assert.assertEquals(order.getSumm(), lt.getSumm());
                notFound = false;
                id = lt.getId();
            }
        }
        if (notFound) {
            Assert.assertEquals(order.getId(), orderDtos[0].getId());
            notFound = false;
        }
        order.setIsReady(true);
        order.setId(id);
        HttpEntity<OrderDto> requestUpdate = new HttpEntity<>(order);
        String url = "http://localhost:8090/orders/update";
        restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, OrderDto.class);
        order.setId(new Long(1));
        response = restTemplate.getForEntity("http://localhost:8090/orders/", OrderDto[].class);
        orderDtos = response.getBody();
        notFound = true;
        for (OrderDto lt : orderDtos) {
            if (lt.getName().equals("ABCD")) {
                Assert.assertEquals(order.getIsReady(), lt.getIsReady());
                notFound = false;
            }
        }
        if (notFound) {
            Assert.assertEquals(order.getId(), orderDtos[0].getId());
            notFound = false;
        }
        url = "http://127.0.0.1:8090/orders/delete/" + Long.toString(id);
        restTemplate.delete(url);
        response = restTemplate.getForEntity("http://localhost:8090/orders/", OrderDto[].class);
        orderDtos = response.getBody();
        for (OrderDto lt : orderDtos) {
            if (lt.getName().equals("ABCD") ) {
                Assert.assertEquals(new Long(-1), lt.getId());
                notFound = true;
            }
        }
        if (notFound) {
            Assert.assertEquals(-1, -1);
        }
    }

}