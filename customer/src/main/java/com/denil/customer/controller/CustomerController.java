package com.denil.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denil.customer.service.CustomerService;
import com.denil.customer.service.TicketService;

import java.util.List;

import com.denil.customer.repository.Customer;
import com.denil.customer.repository.Ticket;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	
	@Autowired
	CustomerService cs;
	@Autowired
	TicketService ts;
	@GetMapping("/profile")
	public String profile() {
		cs.profileData();
		return "from profile";
	}
	
	@GetMapping("/getTicket/pnr/{pnr}")
	public List<Ticket> BookedTicket(@PathVariable String pnr) {
		return cs.BookedTicketDetails(pnr);
		//return "from booked Ticket";
	}
	@PostMapping("/bookticket/flightId/{flightId}")
	public String bookTicket(@RequestBody Ticket t1, @PathVariable int flightId) {
		ts.BookTicket(t1,flightId);
		return "Ticket Booked";
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
	
}
