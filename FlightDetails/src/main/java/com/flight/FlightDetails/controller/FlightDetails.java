package com.flight.FlightDetails.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.FlightDetails.repository.Flight;
import com.flight.FlightDetails.service.FlightService;

@RestController


@RequestMapping("/flightservice")
public class FlightDetails {
	@Autowired
	FlightService fs;
	@Value("${server.port}")
	private String port;
	@GetMapping("/all")
	public String getListAll() {
		return "flight list from port number "+port;
	}
	@GetMapping("/flight/all")
	public List<Flight> getListAllDetails() {
		return fs.getAllFlight();
	}
	
	@GetMapping("/flight/src/{source}/dest/{desti}/date/{date}")
	public List<Flight> getAllFlightSourceDestination(@PathVariable String source,@PathVariable String desti,@PathVariable String date) throws ParseException{
		System.out.println(" DATEEEEEEEE"+date);
		return fs.getAllFlightSourceDestination(source.toUpperCase(), desti.toUpperCase(),date);
	}
	@GetMapping("/flight/src/{source}")
	public List<Flight> getAllFlightBasedOnOnlySource(@PathVariable String source) throws ParseException{
		return fs.getAllFlightBasedOnOnlySource(source.toUpperCase());
	}
	
	@GetMapping("/flight/dest/{destination}")
	public List<Flight> getAllFlightBasedOnOnlyDestination(@PathVariable String dest) throws ParseException{
		return fs.getAllFlightBasedOnOnlyDestination(dest.toUpperCase());
	}
	@GetMapping("/flight/dest/{desti}/date/{date}")
	public List<Flight> getAllFlightBasedOnDestination(@PathVariable String desti,@PathVariable String date) throws ParseException{
		System.out.println(" DATEEEEEEEE"+date);
		return fs.getAllFlightBasedOnDestination( desti.toUpperCase(),date);
	}
	@PostMapping("/flight/add")
	public String addFlightDetails(@RequestBody Flight f1,@RequestHeader (value= "userrole", required=false)Optional<String>  userrole)
	{
		//String flightNo,String src,String des, LocalDate scheduledStartTime,LocalDate scheduledEndTime,LocalDate actualStartTime,LocalDate actualEndTime
		if(!userrole.isPresent())return "not authenticated";
		
		if(userrole.get().compareTo("ADMIN")==0) {
			if(fs.getDuplicateFlightDetails(f1)) {
				return "Record already present in 4 hour span check ";
			}
		fs.addNewFleet(f1);
		return "Inserted";}
		else {return "not authenticated";}
		
				
	}
	@GetMapping("/cancelled-flight/all")
	public String CancelledFlightList() {
		return "cacelled flight";
	}
	@PutMapping("/ticketBooked/updateRemaining/flightId/{flightId}/decrementBy/{decrement}")
	public String updateFlightRemainingTicket(@PathVariable int flightId, @PathVariable int decrement){
		Optional<Flight> f1=fs.getFlightDetails(flightId);
	if(f1.isPresent()) {
		Flight temp=f1.get();
		temp.setAvailableSeat(temp.getAvailableSeat()+decrement);
		fs.flightSeatUpdate(temp);
	}
	else {return "flight details not found";}
	
		return "updated with seat number "+f1.get().getAvailableSeat()+decrement;
		
	}
	
	
	
	//check flight details for seat avalability 
	@GetMapping("/flight/src/flightId/{flightId}/seat/{seat}")
	public String checkFlightAvailability(@PathVariable int  flightId,@PathVariable String seat) throws ParseException{
		Optional<Flight> f1=fs.getFlightDetails(flightId);
		if(f1.isPresent()) {
			Flight temp=f1.get();
			return String.valueOf(temp.getAvailableSeat());
		}
		else {
			return "flight details not found";
			}
		
			//return "updated with seat number "+f1.get().getAvailableSeat()+;
	}
	@GetMapping("/flight/upcoming")
	public List<Flight> getUpcomingFLight() {
		return fs.getAllUpcomingFlight();
	}
}
