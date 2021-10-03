package com.denil.aircraft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denil.aircraft.repository.AircraftRepository;
import com.denil.aircraft.repository.Aircrafts;
import com.denil.aircraft.service.AircraftService;

@RestController

@RequestMapping("/aircraft")
public class AircraftController {

	@Autowired
	AircraftService as;
	@Autowired
	AircraftRepository ar;
	//get all aircraft
	@GetMapping("/all")
	public List<Aircrafts> getAircraftList() {
		return as.getAircraftList();
	}
	@PostMapping("/add")
	public String addAircraft(@RequestBody Aircrafts A1) {
		List <Aircrafts>l1=as.getAircraftByName(A1.getCompanyName());
		if(l1.size()>0) {
			return "Aircraft record already presents";
		}
		
		as.addAircraft(A1);
		return "addAircraft";
	}
	@GetMapping("/name/{name}")
	public List<Aircrafts> getAircraftBasedOnCompany(@PathVariable String name) {
		return as.getAircraftByName(name);
	}
	/*
	  @PutMapping("/employees/{id}")
	  Aircrafts replaceEmployee(@RequestBody Aircrafts aircraft, @PathVariable int id) {
	    
		  Optional<Item> Aircrafts = as.update(id, aircraft);

	        return updated
	                .map(value -> ResponseEntity.ok().body(value))
	                .orElseGet(() -> {
	                    Item created = service.create(updatedItem);
	                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                            .path("/{id}")
	                            .buildAndExpand(created.getId())
	                            .toUri();
	                    return ResponseEntity.created(location).body(created);
	                });
	  }
*/
	  @DeleteMapping("/employees/{id}")
	  void deleteEmployee(@PathVariable int id) {
	    ar.deleteById(id);
	  }
	
	
}
