package com.flight.FlightDetails.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>,FlightCustomRepository {

	List<Flight> findBySourceAndDestination(String source,String deastination);
List<Flight> findBySourceAndDestinationAndFlightNoAndScheduledStartTimeBetween(String src,String des,String flightNo,Date from,Date to);

List<Flight> findBySourceAndDestinationAndScheduledStartTimeBetween(String src,String des,Date from,Date to);
List<Flight> findBySourceAndScheduledStartTimeBetween(String des,Date from,Date to);
List<Flight> findByDestinationAndScheduledStartTimeBetween(String src,Date from,Date to);
	 //@Query("SELECT p FROM itemDetails p WHERE LOWER(p.StockAvailable) = LOWER(:StockAvailable)")
	List<Flight> findByFlightNo(String number);
	
	List<Flight> findBySource(String src);
	List<Flight> findByDestination(String dest);
	List<Flight> findByScheduledStartTimeGreaterThan(Date currentDate);

}
