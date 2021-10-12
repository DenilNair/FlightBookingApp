package com.denil.aircraft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denil.aircraft.repository.AircraftRepository;
import com.denil.aircraft.repository.Aircrafts;

@Service
public class AircraftService {

	@Autowired
	AircraftRepository ar;
	public List<Aircrafts> getAircraftList() {
		
		return ar.findAll();
	}
	public String addAircraft(Aircrafts a) {
		ar.save(a);
		return "addAircraft";
	}
	
	public List<Aircrafts> getAircraftByName(String name){
		return ar.findByCompanyName(name);
	}
	public String update(int id,Aircrafts a) {
		
		ar.save(a);
		return "addAircraft";
	}
	
	public List<Aircrafts> findByAircraftCodeAndId(String name,int id){
		return ar.findByCompanyNameAndId(name, id);
	}
}
