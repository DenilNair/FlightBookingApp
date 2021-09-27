package com.flight.FlightDetails.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

@Repository
public class FlightCustomRepositoryImpl implements FlightCustomRepository{
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Boolean findDuplcateFlight(Flight fl){
		 Query query = em.createNativeQuery("SELECT fl.* FROM denil.flight fl " +
	                "WHERE fl.flight_no = ? and fl.source=? and fl.destination=? ");
		 query.setParameter(1, fl.getFlightNo() + "%");   
		 query.setParameter(2, fl.getSource() + "%");
	        query.setParameter(3, fl.getDestination() + "%");
	        //query.setParameter(4, fl.getScheduledStartTime().toString() + "%");

	       // query.setParameter(5, fl.getScheduledStartTime().toString() + "%");	        

	        @SuppressWarnings("unchecked")
			List<Object[]> resultList = query.getResultList();
			if (resultList.size()>0) {return true;}
			else return false;
	}

}
