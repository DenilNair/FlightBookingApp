package com.denil.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.denil.customer.repository.Customer;
import com.denil.customer.repository.CustomerRepository;
import com.denil.customer.repository.Ticket;
import com.denil.customer.repository.TicketRepository;

@Service
public class CustomerService {
	
	@Autowired
	TicketRepository tr;
	@Autowired
	CustomerRepository cr;
	
	@Autowired
	   RestTemplate restTemplate;
	
	public String profileData() {
		return "from profile";
	}
	
	public List<Ticket> BookedTicketDetails(String pnr) {
		 return tr.findByPnr(pnr);
		//return "from booked Ticket";
	}
	
	
	public String CancelTicket() {
		return " Ticket canceled";
	}
public String NewProfile(Customer c1) {
	cr.save(c1);
	return "profile added";
}
public Customer findbyCustomerId(int id) {
return cr.findByCustomerId(id)	;
}



	
}
