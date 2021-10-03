package com.denil.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
public List<Booking> findByCustomerId(int id);
}
