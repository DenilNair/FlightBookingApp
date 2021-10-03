package com.denil.customer.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	private String pnr;
	private int customerId;
	private String source;
	private String destination;
	private Date scheduledStartTime;
	private String passangerName;
	private int passengerAge;
	private int bookingId;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getPassangerName() {
		return passangerName;
	}
	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ticket(int customerId, String source, String destination, Date scheduledStartTime, Date scheduledEndTime,
			Date actualStartTime, Date actualEndTime, Long ticketFare, String status, String flightNo,String pnr,String passangerName,int age,int bookingId) {
		super();
		this.customerId = customerId;
		this.source = source;
		this.destination = destination;
		this.scheduledStartTime = scheduledStartTime;
		this.scheduledEndTime = scheduledEndTime;
		this.actualStartTime = actualStartTime;
		this.actualEndTime = actualEndTime;
		this.ticketFare = ticketFare;
		this.status = status;
		this.flightNo=flightNo;
		this.pnr = pnr;
		this.passengerAge=age;
		this.passangerName=passangerName;
		this.bookingId=bookingId;
		
	}
	private Date scheduledEndTime;
	private Date actualStartTime;
	private Date actualEndTime;
	private Long ticketFare;
	private String status;
	private String flightNo;
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public Date getScheduledStartTime() {
		return scheduledStartTime;
	}
	public void setScheduledStartTime(Date scheduledStartTime) {
		this.scheduledStartTime = scheduledStartTime;
	}
	public Date getScheduledEndTime() {
		return scheduledEndTime;
	}
	public void setScheduledEndTime(Date scheduledEndTime) {
		this.scheduledEndTime = scheduledEndTime;
	}
	public Date getActualStartTime() {
		return actualStartTime;
	}
	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	public Date getActualEndTime() {
		return actualEndTime;
	}
	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	public Long getTicketFare() {
		return ticketFare;
	}
	public void setTicketFare(Long ticketFare) {
		this.ticketFare = ticketFare;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
