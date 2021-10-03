package com.denil.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.denil.customer.service.CustomerService;
import com.denil.customer.service.TicketService;

import java.util.Collections;
import java.util.List;

import com.denil.customer.repository.Booking;
import com.denil.customer.repository.Customer;
import com.denil.customer.repository.Ticket;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService cs;
	@Autowired
	TicketService ts;
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/profile")
	public String profile() {
		cs.profileData();
		return "from profile";
	}

	@GetMapping("/getTicket/pnr/{pnr}")
	public List<Ticket> BookedTicket(@PathVariable String pnr) {
		return cs.BookedTicketDetails(pnr);
		// return "from booked Ticket";
	}

	@PostMapping("/bookticket/flightId/{flightId}")
	public String bookTicket(@RequestBody List<Ticket> t1, @PathVariable int flightId,
			@RequestHeader(value = "Authorization") String authorizationHeader) {
		// first check user is authenticated or not
		HttpHeaders httpHeaders1 = new HttpHeaders();
		httpHeaders1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders1.set("Authorization", authorizationHeader);
		HttpEntity<String> entity1 = new HttpEntity<String>(httpHeaders1);
		// check flight available or not if not respond back with message flight not
		// available
		String temp = restTemplate.exchange("http://localhost:9192/", HttpMethod.GET, entity1, String.class).toString();
		String tokenChecktemp = temp.split(",")[1];
		if (tokenChecktemp.equals("Invalid Token")) {
			return tokenChecktemp;
		} else {
			String message = ts.BookTicket(t1, flightId);
			return message;
		}

	}

	@DeleteMapping("/cancel-ticket")
	public String CancelTicket(@PathVariable String pnr) {
		cs.CancelTicket();
		return " Ticket canceled";
	}

	@PostMapping("/newProfile")
	public String newProfile(@RequestBody Customer c1) {
		cs.NewProfile(c1);
		return "new profile created";
	}

	@GetMapping("/viewProfile/id/{customerId}")
	public Customer viewProfile(@PathVariable int customerId) {
		return cs.findbyCustomerId(customerId);
	}

	@GetMapping("/viewTicket/id/{customerId}")
	public List<Booking> viewTicket(@PathVariable int customerId,@RequestHeader(value = "Authorization") String authorizationHeader) {
		
		// first check user is authenticated or not
				HttpHeaders httpHeaders1 = new HttpHeaders();
				httpHeaders1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				httpHeaders1.set("Authorization", authorizationHeader);
				HttpEntity<String> entity1 = new HttpEntity<String>(httpHeaders1);
				// check flight available or not if not respond back with message flight not
				// available
				String temp = restTemplate.exchange("http://localhost:9192/", HttpMethod.GET, entity1, String.class).toString();
				String tokenChecktemp = temp.split(",")[1];
				if (tokenChecktemp.equals("Invalid Token")) {
					return null;
				} else {
					return ts.findTicketById(customerId);
				}
	
	}

	@GetMapping("/viewTicket/bookingId/{bookingId}")
	public List<Ticket> viewTicketDetails(@PathVariable int bookingId,@RequestHeader(value = "Authorization") String authorizationHeader) {
		// first check user is authenticated or not
		HttpHeaders httpHeaders1 = new HttpHeaders();
		httpHeaders1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders1.set("Authorization", authorizationHeader);
		HttpEntity<String> entity1 = new HttpEntity<String>(httpHeaders1);
		// check flight available or not if not respond back with message flight not
		// available
		String temp = restTemplate.exchange("http://localhost:9192/", HttpMethod.GET, entity1, String.class).toString();
		String tokenChecktemp = temp.split(",")[1];
		if (tokenChecktemp.equals("Invalid Token")) {
			return null;
		} else {
			return ts.findTicketDetailsList(bookingId);
		}
		
	}

}
