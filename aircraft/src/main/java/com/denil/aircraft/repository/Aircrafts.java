package com.denil.aircraft.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aircrafts {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	public Aircrafts( String companyName, String aircraftType, int sittingCapacity, String aircraftCode) {
		super();
	
		this.companyName = companyName;
		this.aircraftType = aircraftType;
		this.sittingCapacity = sittingCapacity;
		this.aircraftCode = aircraftCode;
	}
	public Aircrafts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAircraftType() {
		return aircraftType;
	}
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
	public int getSittingCapacity() {
		return sittingCapacity;
	}
	public void setSittingCapacity(int sittingCapacity) {
		this.sittingCapacity = sittingCapacity;
	}
	public String getAircraftCode() {
		return aircraftCode;
	}
	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}
	private String companyName;
	private String aircraftType;
	private int sittingCapacity;
	//BA-777 (company + type)
	private String aircraftCode;
	
	
}
