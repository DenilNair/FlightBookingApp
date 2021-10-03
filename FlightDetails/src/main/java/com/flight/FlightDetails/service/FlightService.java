package com.flight.FlightDetails.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.FlightDetails.repository.Flight;
import com.flight.FlightDetails.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	FlightRepository flightRep;
	public List<Flight> getAllFlight() {
	List<Flight>l1=	flightRep.findAll();
	return l1;
	}
	public List<Flight>  getAllFlightSourceDestination(String src,String des,String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");//15-Sep-2021 14:52:00
		Date d1 = formatter.parse(date);
		Date d2= d1;
		 // Substract 2 hour from the current time
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
        calendar.add(Calendar.HOUR, 23);
 
        // Add 30 minutes to the calendar time
        calendar.add(Calendar.MINUTE, 59);
 
        // Add 300 seconds to the calendar time
        calendar.add(Calendar.SECOND, 59);
        d2=calendar.getTime();
		List<Flight>l1=	flightRep.findBySourceAndDestinationAndScheduledStartTimeBetween(src,des,d1,d2);
		System.out.println("Start time "+d1);
		System.out.println("ENd time "+d2);
		return l1;
	}
	
	public List<Flight>  getAllFlightBasedOnDestination(String des,String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");//15-Sep-2021 14:52:00
		Date d1 = formatter.parse(date);
		Date d2= d1;
		 // Substract 2 hour from the current time
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
        calendar.add(Calendar.HOUR, 23);
 
        // Add 30 minutes to the calendar time
        calendar.add(Calendar.MINUTE, 59);
 
        // Add 300 seconds to the calendar time
        calendar.add(Calendar.SECOND, 59);
        d2=calendar.getTime();
		List<Flight>l1=	flightRep.findByDestinationAndScheduledStartTimeBetween(des,d1,d2);
		System.out.println("Start time "+d1);
		System.out.println("ENd time "+d2);
		return l1;
	}
	
	public List<Flight>  getAllFlightBasedOnSource(String src,String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");//15-Sep-2021 14:52:00
		Date d1 = formatter.parse(date);
		Date d2= d1;
		 // Substract 2 hour from the current time
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
        calendar.add(Calendar.HOUR, 23);
 
        // Add 30 minutes to the calendar time
        calendar.add(Calendar.MINUTE, 59);
 
        // Add 300 seconds to the calendar time
        calendar.add(Calendar.SECOND, 59);
        d2=calendar.getTime();
		List<Flight>l1=	flightRep.findBySourceAndScheduledStartTimeBetween(src,d1,d2);
		System.out.println("Start time "+d1);
		System.out.println("ENd time "+d2);
		return l1;
	}
	public void addNewFleet(Flight f1)
	{
	flightRep.save(f1);	
	
	}
	public boolean getDuplicateFlightDetails(Flight f) {
		Boolean  f1=flightRep.findDuplcateFlight(f);
		Date start=f.getScheduledStartTime();
		Date end=f.getScheduledStartTime();
		//start.setTime(start.getTime()-(1*(3600*1000)));
		Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.HOUR, -4);
        start=c.getTime();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        c1.add(Calendar.HOUR, +4);
        end=c1.getTime();
		//end.setTime(end.getTime()+(1*(3600*1000)));
		List<Flight> l1=flightRep.findBySourceAndDestinationAndFlightNoAndScheduledStartTimeBetween(f.getSource(), f.getDestination(), f.getFlightNo(),start,end);
		if(l1.size()>0)return true;
		return f1;
	}
	public void getFlightFromAirport(String airportCode) {}
	public Optional<Flight> getFlightDetails(int FlightNumber) {
		return flightRep.findById(FlightNumber);
	}
	public void flightSeatUpdate(Flight updatedFlight) {
		flightRep.save(updatedFlight);
	}
	
	
}
