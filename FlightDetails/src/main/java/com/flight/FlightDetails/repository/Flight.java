package com.flight.FlightDetails.repository;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Flight {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String flightNo;
	private String source;
	private String destination;
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")

	private Date scheduledStartTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date scheduledEndTime;

	private int availableSeat;
	public int getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight( String flightNo, String source, String destination, Date scheduledStartTime,
			Date scheduledEndTime,int availableSeat,String status) {
		super();
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
		this.scheduledStartTime = scheduledStartTime;
		this.scheduledEndTime = scheduledEndTime;
		this.availableSeat=availableSeat;
		this.status=status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
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
	
}
