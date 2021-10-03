package com.denil.customer.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

@Entity
public class Booking {

	
	public int getBookingId() {
		return bookingId;
	}
	public void setBooking_id(int booking_id) {
		this.bookingId = booking_id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCustomer_id() {
		return customerId;
	}
	public void setCustomer_id(int customer_id) {
		this.customerId = customer_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTotalPasenger() {
		return totalPasenger;
	}
	public void setTotalPasenger(String totalPasenger) {
		this.totalPasenger = totalPasenger;
	}
	public Date getBoardingDate() {
		return boardingDate;
	}
	public void setBoardingDate(Date boardingDate) {
		this.boardingDate = boardingDate;
	}
	public String getGateNumber() {
		return gateNumber;
	}
	public void setGateNumber(String gateNumber) {
		this.gateNumber = gateNumber;
	}
	public Booking(int customer_id, String firstname, String source, String destination, String totalPasenger,
			Date boardingDate, String gateNumber,int customerid) {
		super();
		this.customerId = customer_id;
		this.firstname = firstname;
		this.source = source;
		this.destination = destination;
		this.totalPasenger = totalPasenger;
		this.boardingDate = boardingDate;
		this.gateNumber = gateNumber;
		this.customerId=customer_id;
	}
	private int customerId;
	private String firstname;
	private String source;
	private String destination;
	private String totalPasenger;
	private Date boardingDate;
	private String gateNumber;
	
}
