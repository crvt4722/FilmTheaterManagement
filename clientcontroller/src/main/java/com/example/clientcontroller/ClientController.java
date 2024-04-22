package com.example.clientcontroller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Controller
public class ClientController {
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/")
	public String home() {
		return "employeeHome";
	}
	
	@GetMapping("/customer-revenue")
	public String customerRevenue(HttpSession session) {
		return "customerRevenueStat";
	}
	
	@GetMapping("/detail-transaction/{id}")
	public String getBill(HttpSession session, @PathVariable int id) {		
		ArrayList<CustomerStat> customer_stats = (ArrayList<CustomerStat>) session.getAttribute("customer_revenue_stat");
		
		for (CustomerStat x: customer_stats) {
			if (x.getId() == id) {
				session.setAttribute("customer_detail_transaction", x);
			}
		}
		
		String startTime = (String) session.getAttribute("start_time");
		String endTime = (String) session.getAttribute("end_time");
		
		String url = "http://localhost:8081/bill" + "?customerId=" + id + "&startTime=" + startTime + "&endTime=" + endTime;
        ResponseEntity<Bill[]> bills_response = restTemplate.getForEntity(url, Bill[].class);
        Bill[] bills_array = bills_response.getBody();
        
        List<Bill> bills = new ArrayList<>();
        for(int i=0;i<bills_array.length;i++){
        	bills.add(bills_array[i]);
        }
        
        session.setAttribute("detail_transaction", bills);
		return "detailTransaction";
	}
	
	@GetMapping("/transaction-info")
	public String transactionInfo() {
		return "transactionInfo";
	}
	
	@GetMapping("/exchange-giftitem")
	public String exchangeGiftItem(HttpSession session) {

		return "exchangeGiftItem";
	}
	
	@GetMapping("/customer-revenue-stat")
	public String getCustomerRevenueStat(HttpSession session, @RequestParam("start_time") String startTime,
										@RequestParam("end_time") String endTime ) {
		
		String url = "http://localhost:8081/customer-revenue-stat?" + "startTime=" + startTime + "&endTime=" + endTime;
//		String responseStr = restTemplate.getForObject(url, String.class);
		
		session.setAttribute("start_time", startTime);
		session.setAttribute("end_time", endTime);
		
        ResponseEntity<BillCustomerStat[]> customer_stats = restTemplate.getForEntity(url, BillCustomerStat[].class);
        BillCustomerStat[] customer_stat_raw = customer_stats.getBody();
        List<BillCustomerStat> result = new ArrayList<>();
        for(int i=0;i<customer_stat_raw.length;i++){
        	result.add(customer_stat_raw[i]);
        }
        
        List<CustomerStat> customer_stats_exchanged = new ArrayList<>();
        int total_revenue = 0;
        
        for (BillCustomerStat x : result) {
        	int customerId = x.getCustomerId();
        	int revenue = x.getRevenue();
        	String period = x.getPeriod();        	
        	
        	url = "http://localhost:8082/customer-revenue-stat?" + "id=" + customerId + "&revenue=" + revenue + "&period=" + period;
        	ResponseEntity<CustomerStat> customer_stat_exchanged = restTemplate.getForEntity(url, CustomerStat.class);        	
        	CustomerStat customerStat = customer_stat_exchanged.getBody();
        	customer_stats_exchanged.add(customerStat);        	
        	total_revenue += revenue;
        }
        
        session.setAttribute("customer_revenue_stat", customer_stats_exchanged);
        session.setAttribute("total_revenue", total_revenue);
        
		return "redirect:/customer-revenue";
	}
	
	@GetMapping("/room")
	public String getAllRoom(HttpSession session) {
		String url = "http://localhost:8083/room";
		ResponseEntity<Room[]> room_response = restTemplate.getForEntity(url, Room[].class);
		Room[] room_arr = room_response.getBody();
        List<Room> result = new ArrayList<>();
        for(int i=0;i<room_arr.length;i++){
        	result.add(room_arr[i]);
        }
                
        
        session.setAttribute("rooms", result);
		return "room";
	}
	
	@GetMapping("/room/detail")
	public String getRoom(HttpSession session, @RequestParam(name = "room_id", required = false) int roomId) {
		String url = "http://localhost:8083/room/" + roomId;
		ResponseEntity<Room> room_response = restTemplate.getForEntity(url, Room.class);
		Room room = room_response.getBody();
        List<Room> result = new ArrayList<>();
        result.add(room);
                
        
        session.setAttribute("rooms", result);
		return "room";
	}
	
	@GetMapping("/seat")
	public String getAllSeat(HttpSession session, @RequestParam int roomId) {
		String url = "http://localhost:8083/seat" + "?roomId=" + roomId;
		ResponseEntity<Seat[]> seat_response = restTemplate.getForEntity(url, Seat[].class);
		Seat[] seat_arr = seat_response.getBody();
        List<Seat> result = new ArrayList<>();
        for(int i=0;i<seat_arr.length;i++){
        	result.add(seat_arr[i]);
        }                
        
        session.setAttribute("seat_url", "/seat" + "?roomId=" + roomId);
        session.setAttribute("seats", result);
		return "seat";
	}
	
	@GetMapping("/seat/detail")
	public String getSeat(HttpSession session, @RequestParam(name = "seat_id", required = false) int seatId) {
		String url = "http://localhost:8083/seat/" + seatId;
		ResponseEntity<Seat> room_response = restTemplate.getForEntity(url, Seat.class);
		Seat seat = room_response.getBody();
        List<Seat> result = new ArrayList<>();
        result.add(seat);
                
        
        session.setAttribute("seats", result);
		return "seat";
	}
	

	
	@GetMapping("/seat/update/{id}")
	public String getSeat(HttpSession session, @PathVariable int id, 
			@RequestParam(name = "seat_type", required = false) String type, 
			@RequestParam(name = "seat_position", required = false) int position) {
		String url = "http://localhost:8083/seat/" + id;
		
		Seat seat = new Seat(id, position, type, null);
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Seat> request = new HttpEntity<>(seat, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/seat/update", request, String.class);
        session.setAttribute("update_seat_response", response.getBody());
                
		return "redirect:" + (String)session.getAttribute("seat_url");
	}
	
	@GetMapping("/customer")
	public String getCustomer(HttpSession session, @RequestParam(name = "customer_id", required = false) int id) {
		String url = "http://localhost:8082/customer/" + id;
		ResponseEntity<Customer> customer_response = restTemplate.getForEntity(url, Customer.class);
		Customer customer = customer_response.getBody();
		
		session.setAttribute("customer_exchange_gift", customer);
		
		url = "http://localhost:8081/gift-item";
		ResponseEntity<GiftItem[]> seat_response = restTemplate.getForEntity(url, GiftItem[].class);
		GiftItem[] seat_arr = seat_response.getBody();
        List<GiftItem> result = new ArrayList<>();
        for(int i=0;i<seat_arr.length;i++){
        	result.add(seat_arr[i]);
        }     
        		
		session.setAttribute("giftItems", result);
		return "exchangeGiftItem";
	}
	
	@PostMapping("/exchange-giftitem-action")
    public String exchangeGiftItem(HttpSession session, @RequestParam(name = "quantity", required = false)  int [] quantities,
    								@RequestParam(name = "selected", required = false)  String [] selections) {
        // Process the form data
		List<GiftItem> giftItems = (ArrayList<GiftItem>) session.getAttribute("giftItems");
		
		List<UsedGiftItem> usedGiftItems = new ArrayList<UsedGiftItem>();
		int total_point_needed = 0;
		for (int i=0 ;i < selections.length; ++i) {		
				System.out.println(selections[i]);
				int pos = Integer.parseInt(selections[i]);
				GiftItem giftItem = giftItems.get(pos);
				int quantity = quantities[pos];
				total_point_needed += giftItem.getNeededPoint() * quantity;
				
				usedGiftItems.add(new UsedGiftItem(0, giftItem, quantity));			
		}
        // Redirect to a success page or return a response
		
		Customer customer = (Customer) session.getAttribute("customer_exchange_gift");
		
		if (customer.getCurrentPoint() >= total_point_needed) {
			session.removeAttribute("used_gift_items");
			session.setAttribute("used_gift_items", usedGiftItems);
			session.setAttribute("total_point_needed", total_point_needed);
			session.removeAttribute("alert_exchange_gift");			
		}
		
		else {
			session.setAttribute("alert_exchange_gift", "Số điểm hiện tại của khách hàng không đủ, vui lòng chọn lại!");
			return "exchangeGiftItem";
		}
        return "transactionInfo";
    }
	
	@PostMapping("/confirm-transaction")
	public String confirmTransaction(HttpSession session) {
		
		ArrayList<UsedGiftItem> usedGiftItems = (ArrayList<UsedGiftItem>) session.getAttribute("used_gift_items");
		int totalPointNeeded = (int) session.getAttribute("total_point_needed");		
		Customer customer = (Customer) session.getAttribute("customer_exchange_gift");
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");		
		String paymentTime = now.format(formatter);
		
		Bill bill = new Bill(0, customer.getId(), 0, paymentTime, 0, 0 - totalPointNeeded, null, usedGiftItems, null);
		
//		Call substract usedpoint to customer service.
		String url = "http://localhost:8082/customer/exchange-giftitem" + "?id=" + customer.getId() + "&usedPoint=" + totalPointNeeded;
		ResponseEntity<String> updateCustomerPointResponse = restTemplate.getForEntity(url, String.class);
		String updateCustomerPointResponseStr = updateCustomerPointResponse.getBody();		
		System.out.println(updateCustomerPointResponseStr);
		
//		Add new bill.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Bill> request = new HttpEntity<>(bill, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/bill/add", request, String.class);
        System.out.println(response.getBody());
        
//		Get customer after change.
		url = "http://localhost:8082/customer/" + customer.getId();
		ResponseEntity<Customer> customer_response = restTemplate.getForEntity(url, Customer.class);
		customer = customer_response.getBody();
		
		session.setAttribute("customer_exchange_gift", customer);
		return "exchangeGiftItem";
	}
}
