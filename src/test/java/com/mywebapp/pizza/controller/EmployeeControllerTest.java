package com.mywebapp.pizza.controller;

import com.mywebapp.pizza.model.Employee;
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
class EmployeeControllerTest {

    @org.junit.jupiter.api.Test
    void test() {
        Employee employee = new Employee(new Long(-1), "Ann Smith", "cashier", "4321", "password");
        long id = employee.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        ResponseEntity<Employee> sent = restTemplate.exchange("http://localhost:8090/employee/add", HttpMethod.POST, request, Employee.class);
        ResponseEntity<Employee[]> response = restTemplate.getForEntity("http://localhost:8090/employee/", Employee[].class);
        Employee[] employees = response.getBody();
        boolean notFound = true;
        for (Employee lt : employees) {
            if (lt.getFcS().equals("Ann Smith")) {
                Assert.assertEquals(employee.getJobName(), lt.getJobName());
                Assert.assertEquals(employee.getPhone(), lt.getPhone());
                Assert.assertEquals(employee.getPassword(), lt.getPassword());
                notFound = false;
                id = lt.getId();
            }
        }
        if (notFound) {
            Assert.assertEquals(employee.getId(), employees[0].getId()); //всегда провалится, вызывается если в список не добавится сотрудница
            notFound = false;
        }
        employee.setJobName("manager");
        employee.setId(id);
        HttpEntity<Employee> requestUpdate = new HttpEntity<>(employee);
        String url = "http://localhost:8090/employee/update";
        restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Employee.class);
        employee.setId(new Long(1));
        response = restTemplate.getForEntity("http://localhost:8090/employee/", Employee[].class);
        employees = response.getBody();
        notFound = true;
        for (Employee lt : employees) {
            if (lt.getFcS().equals("Ann Smith")) {
                Assert.assertEquals(employee.getJobName(), lt.getJobName());
                notFound = false;
            }
        }
        if (notFound) {
            Assert.assertEquals(employee.getId(), employees[0].getId()); //всегда провалится, вызывается если сотрудница не обновилась
            notFound = false;
        }
        url = "http://127.0.0.1:8090/employee/delete/" + Long.toString(id);
        restTemplate.delete(url);
        response = restTemplate.getForEntity("http://localhost:8090/employee/", Employee[].class);
        employees = response.getBody();
        for (Employee lt : employees) {
            if (lt.getFcS().equals("Anna Smith")) {
                Assert.assertEquals(new Long(-1), lt.getId()); //если после удаления сотрудница осталась
                notFound = true;
            }
        }
        if (notFound) {
            Assert.assertEquals(-1, -1); //если сотрудницы уже нет
        }
    }
}