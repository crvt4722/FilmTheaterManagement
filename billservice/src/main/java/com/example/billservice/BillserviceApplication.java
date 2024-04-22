package com.example.billservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.*;
import model.*;

import java.util.*;
@RestController
@SpringBootApplication
public class BillserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillserviceApplication.class, args);
	}
	
    @GetMapping("/customer-revenue-stat")
    public String getRevenueByCustomer(@RequestParam String startTime, @RequestParam String endTime ) throws JsonProcessingException {
    	BillDAO billDAO = new BillDAO();
    	ArrayList<BillCustomerStat> result = billDAO.getCustomerRevenueStat(startTime, endTime);
    	
    	ObjectMapper obj = new ObjectMapper();    	
    	return (String) obj.writeValueAsString(result);
    }  
    
    @GetMapping("/bill")
    public String getBillByCustomer(@RequestParam int customerId, @RequestParam String startTime, @RequestParam String endTime)
    		throws JsonProcessingException {
    	BillDAO billDAO = new BillDAO();
    	ArrayList<Bill> bills = billDAO.getBill(customerId, startTime, endTime);
    	

    	ObjectMapper obj = new ObjectMapper();    	
    	return (String) obj.writeValueAsString(bills);    	
    }
    
    @GetMapping("/gift-item")
    public String getAllGiftItem() throws JsonProcessingException {
    	GiftItemDAO giftItemDAO = new GiftItemDAO();
    	ArrayList<GiftItem> giftItems = giftItemDAO.getAll();
    	
    	ObjectMapper obj = new ObjectMapper();    	
    	return (String) obj.writeValueAsString(giftItems);   
    }
    
    @PostMapping("/bill/add")
    public ResponseEntity<String> updateSeat(@RequestBody Bill bill) {   
    	BillDAO billDAO = new BillDAO();
    	
    	billDAO.add(bill);
	    // Optionally, you can return a response
	    return ResponseEntity.ok("Bill add successfully");
    }
}
