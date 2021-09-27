package com.denil.customer.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.denil.customer.repository.Ticket;
import com.denil.customer.repository.TicketRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
@Service
@Transactional
public class TicketService {

	@Autowired
	TicketRepository tr;
	
	@Autowired
	   RestTemplate restTemplate;

	public List<Ticket> BookedTicketDetails(String pnr) {
		 return tr.findByPnr(pnr);
		//return "from booked Ticket";
	}
	@SuppressWarnings("deprecation")
	public String BookTicket(Ticket ts,int flightId) {
		ts.setPnr(ts.getCustomerId()+ts.getSource()+ts.getDestination()+ts.getScheduledStartTime().getDay()+ts.getScheduledStartTime().getMonth()+ts.getScheduledStartTime().getYear()+ts.getScheduledStartTime().getHours()+ts.getScheduledStartTime().getMinutes());
		  tr.save(ts);
		  HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        HttpEntity<Ticket> entity = new HttpEntity<>(ts, httpHeaders);
	        return restTemplate.exchange("http://localhost:9091/flightservice/ticketBooked/updateRemaining/flightId/"+flightId+"/decrementBy/"+1, HttpMethod.PUT, entity, String.class).getBody();
	   
	      
	      //return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
	   
		//return "Ticket booked";
	}
}
