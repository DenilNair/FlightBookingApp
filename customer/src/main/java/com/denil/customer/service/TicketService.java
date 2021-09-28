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
	public String BookTicket(List<Ticket> listTicket,int flightId) {
		Ticket ts1=listTicket.get(0);
		  HttpHeaders httpHeaders1 = new HttpHeaders();
	        httpHeaders1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        HttpEntity<Ticket> entity1 = new HttpEntity<>(ts1, httpHeaders1);
        //check flight available or not if not respond back with message flight not available
		      String flightSeatCheck=  restTemplate.exchange("http://localhost:9091/flightservice/flight/src/flightId/"+flightId+"/seat/"+(listTicket.size()+1), HttpMethod.GET, entity1, String.class).getBody();
				  
				   
					  try {
								  int k= Integer.parseInt(flightSeatCheck);
							  if(k<listTicket.size())
								  return "Only "+k+" flight tickets remaining check any other flight";
					  	}catch(Exception e) {
					 if(flightSeatCheck.compareTo("flight details not found")==0) {
						  return "flight details not found";
					  } 
				 }
		for(int i=0;i<listTicket.size();i++) {
			Ticket ts=listTicket.get(i);
			ts.setPnr(ts.getCustomerId()+ts.getSource()+ts.getDestination()+ts.getScheduledStartTime().getDay()+ts.getScheduledStartTime().getMonth()+ts.getScheduledStartTime().getYear()+ts.getScheduledStartTime().getHours()+ts.getScheduledStartTime().getMinutes());
			  tr.save(ts);
			
		
					   
		      
		  
		}
		 
	     
		  //after ticket booked reduce the number of remaining ticket in particular flight
        restTemplate.exchange("http://localhost:9091/flightservice/ticketBooked/updateRemaining/flightId/"+flightId+"/decrementBy/"+(-listTicket.size()), HttpMethod.PUT, entity1, String.class).getBody();
  
		    
	      //return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
	   
		return "Ticket booked";
	}
}
