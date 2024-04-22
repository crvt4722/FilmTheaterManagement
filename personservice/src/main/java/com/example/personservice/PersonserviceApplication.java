package com.example.personservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CustomerDAO;
import dao.CustomerStatDAO;
import model.BillCustomerStat;
import model.Customer;
import model.CustomerStat;

import java.util.*;
@RestController
@SpringBootApplication
public class PersonserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonserviceApplication.class, args);
	}
	
    @GetMapping("/customer-revenue-stat")    
    public String getCustomerRevenueStat(@RequestParam int id, @RequestParam int revenue, @RequestParam String period ) throws JsonProcessingException {
    	CustomerStatDAO customerStatDAO = new CustomerStatDAO();
    	CustomerStat customerStat = customerStatDAO.getCustomerStat(id, revenue, period);
    	
    	ObjectMapper mapper = new ObjectMapper();    
    	return (String) mapper.writeValueAsString(customerStat);    	
    }  
    
    @GetMapping("/customer/{id}")    
    public String getCustomer(@PathVariable int id ) throws JsonProcessingException {
    	CustomerDAO customerDAO = new CustomerDAO();
    	Customer customer = customerDAO.getCustomer(id);
    	
    	ObjectMapper mapper = new ObjectMapper();    
    	return (String) mapper.writeValueAsString(customer);    	
    }  
    
    @GetMapping("/customer/exchange-giftitem")    
    public String exchangeGiftItem(@RequestParam int id, @RequestParam int usedPoint ) throws JsonProcessingException {
    	CustomerDAO customerDAO = new CustomerDAO();
    	customerDAO.exchangeGiftItem(id, usedPoint);
    	
    	return "Substract current point for exchanging giftitem";	
    }  
}
