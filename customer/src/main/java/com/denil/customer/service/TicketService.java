package com.denil.customer.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.denil.customer.repository.Booking;
import com.denil.customer.repository.BookingRepository;
import com.denil.customer.repository.Customer;
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
	BookingRepository br;

	@Autowired
	RestTemplate restTemplate;

	public List<Ticket> BookedTicketDetails(String pnr) {
		return tr.findByPnr(pnr);
		// return "from booked Ticket";
	}

	@SuppressWarnings("deprecation")
	public String BookTicket(List<Ticket> listTicket, int flightId,int customerId) {
		Ticket ts1 = listTicket.get(0);

		ts1.setCustomerId(customerId);
		HttpHeaders httpHeaders1 = new HttpHeaders();
		httpHeaders1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Ticket> entity1 = new HttpEntity<>(ts1, httpHeaders1);
		// check flight available or not if not respond back with message flight not
		// available
		String flightSeatCheck = restTemplate.exchange("http://localhost:9091/flightservice/flight/src/flightId/"
				+ flightId + "/seat/" + (listTicket.size() + 1), HttpMethod.GET, entity1, String.class).getBody();

		try {
			int k = Integer.parseInt(flightSeatCheck);
			if (k < listTicket.size())
				return "Only " + k + " flight tickets remaining check any other flight";
		} catch (Exception e) {
			if (flightSeatCheck.compareTo("flight details not found") == 0) {
				return "flight details not found";
			}
		}
		// first insert record to booking table
		Booking tempBooking=new Booking();
		tempBooking.setBoardingDate(ts1.getScheduledStartTime());
		tempBooking.setCustomer_id(ts1.getCustomerId());
		tempBooking.setSource(ts1.getSource());
		tempBooking.setDestination(ts1.getDestination());
		tempBooking.setFirstname(ts1.getPassangerName());
		tempBooking.setTotalPasenger(String.valueOf(listTicket.size()));
		Booking newBooking=br.save(tempBooking);
		int bookingId=newBooking.getBookingId();
		for (int i = 0; i < listTicket.size(); i++) {

			Ticket ts = listTicket.get(i);
			ts.setBookingId(bookingId);
			/*
			List<Ticket> temp = tr.findByCustomerIdAndFlightNoAndBookingId(ts.getCustomerId(),
					ts.getFlightNo(), bookingId);
			if (temp.size() > 0) {
				return "Ticket Already Booked for same passanger. Cancel it to Book Again";
			}*/
			ts.setPnr(ts.getBookingId() + ts.getSource() + ts.getDestination() + ts.getScheduledStartTime().getDay()
					+ ts.getScheduledStartTime().getMonth() + ts.getScheduledStartTime().getYear()
					+ ts.getScheduledStartTime().getHours() + ts.getScheduledStartTime().getMinutes());
			if(ts.getMeal()=="" ) {
				ts.setMeal("No");
			}
			tr.save(ts);

		}

		// after ticket booked reduce the number of remaining ticket in particular
		// flight
		restTemplate.exchange("http://localhost:9091/flightservice/ticketBooked/updateRemaining/flightId/" + flightId
				+ "/decrementBy/" + (-listTicket.size()), HttpMethod.PUT, entity1, String.class).getBody();

		// return restTemplate.exchange("http://localhost:8080/products",
		// HttpMethod.GET, entity, String.class).getBody();

		return "Ticket booked";
	}

	public List<Booking> findTicketById(int id) {
		return br.findByCustomerId(id);
	}
	public List<Ticket> findTicketDetailsList(int id) {
		return tr.findByBookingId(id);
	}

}
