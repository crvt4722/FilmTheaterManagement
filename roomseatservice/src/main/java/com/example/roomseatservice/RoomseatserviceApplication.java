package com.example.roomseatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.RoomDAO;
import dao.SeatDAO;
import model.*;

import java.util.*;
@RestController
@SpringBootApplication
public class RoomseatserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomseatserviceApplication.class, args);
	}
	
    @GetMapping("/room")
    public String getAll( ) throws JsonProcessingException {
    	RoomDAO roomDAO = new RoomDAO();
    	ArrayList<Room> result = roomDAO.getAll();
    	
    	ObjectMapper obj = new ObjectMapper();    	
    	return (String) obj.writeValueAsString(result);
    }  
    
    @GetMapping("/room/{id}")
    public String getRoom( @PathVariable int id) throws JsonProcessingException {
    	RoomDAO roomDAO = new RoomDAO();
    	Room result = roomDAO.getRoom(id);
    	
    	ObjectMapper obj = new ObjectMapper();    	
    	return (String) obj.writeValueAsString(result);
    }  
    
    @GetMapping("/seat")
    public String getAll(@RequestParam int roomId ) throws JsonProcessingException {
    	SeatDAO seatDAO = new SeatDAO();
    	ArrayList<Seat> result = seatDAO.getAll(roomId);
    	
    	ObjectMapper obj = new ObjectMapper();    	
    	return (String) obj.writeValueAsString(result);
    }  
    
    @GetMapping("/seat/{id}")
    public String getSeat(@PathVariable int id ) throws JsonProcessingException {
    	SeatDAO seatDAO = new SeatDAO();
    	Seat result = seatDAO.getSeat(id);
    	
    	ObjectMapper obj = new ObjectMapper();    	
    	return (String) obj.writeValueAsString(result);
    }  
    
    @PostMapping("/seat/update")
    public ResponseEntity<String> updateSeat(@RequestBody Seat seat) {   
    	SeatDAO seatDAO = new SeatDAO();
    	seatDAO.updateSeat(seat);
	    System.out.println("Received car: " + seat.toString());
	    
	
	    // Optionally, you can return a response
	    return ResponseEntity.ok("Car received successfully");
    }
}
    